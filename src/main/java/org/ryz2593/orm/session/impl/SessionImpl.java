package org.ryz2593.orm.session.impl;

import org.ryz2593.orm.datasource.DataSource;
import org.ryz2593.orm.datasource.impl.DataSourceImpl;
import org.ryz2593.orm.jpa.Column;
import org.ryz2593.orm.jpa.Table;
import org.ryz2593.orm.session.Session;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author ryz2593
 * @date 2019/4/23
 * @desc
 */
public class SessionImpl implements Session {

    private DataSource dataSource;

    public SessionImpl() {
        //初始化了一个数据源对象
        this.dataSource = new DataSourceImpl();
    }

    @Override
    public <T> int save(T entity) {
        if (dataSource == null) {
            throw new RuntimeException("数据源未被初始化");
        }

        return fireSave(entity);
    }

    private <T> int fireSave(T entity) {
        int row = 0;
        //构建sql语句及对应占位符对应参数列表
        StringBuilder sqlBuilder = new StringBuilder();
        List<Object> parameterList = new ArrayList<>();

        generateSqlAndParameters(entity, sqlBuilder, parameterList);
       try(
               Connection conn = dataSource.getDataSource();
               PreparedStatement pst = conn.prepareStatement(sqlBuilder.toString())
               ) {
           if (parameterList.size() > 0) {
               for (int i = 0; i < parameterList.size(); i++) {
                   pst.setObject(i + 1, parameterList.get(i));
               }
           }
           row = pst.executeUpdate();

       } catch (Exception e) {
           e.printStackTrace();
       }
       return row;
    }

    /**
     * 生成操作实现对象对应sql以及参数数组
     * @param entity
     * @param sqlBuilder
     * @param parameterList
     * @param <T>
     *     inset into table (column,...) values (?,?,?)
     */
    private <T> void generateSqlAndParameters(T entity, StringBuilder sqlBuilder, List<Object> parameterList) {

        //获取实体映射表名称（配置了映射名称，没有配置映射名称）
        getTableName(entity, sqlBuilder);
        getColumnName(entity, sqlBuilder, parameterList);

    }

    private <T> void getColumnName(T entity, StringBuilder sqlBuilder, List<Object> parameterList) {
        Field[] fields = entity.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                //获取列名
                String fieldName = field.getName();
                String columnName = fieldName;
                if (field.isAnnotationPresent(Column.class)) {
                    Column column = field.getAnnotation(Column.class);
                    if (!Objects.equals("", column.value())) {
                        columnName = column.value();
                    }
                }
                //开启字段的访问权限
                field.setAccessible(true);
                //获取字段的值
                Object value = field.get(entity);
                if (value != null) {
                    sqlBuilder.append(columnName).append(",");
                    parameterList.add(value);
                }
            }
            sqlBuilder.deleteCharAt(sqlBuilder.length() - 1).append(") values (");
            for (Object param : parameterList) {
                sqlBuilder.append("?,");
            }
            sqlBuilder.deleteCharAt(sqlBuilder.length() - 1).append(")");

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(sqlBuilder.toString());
        System.out.println(parameterList);

    }

    private <T> void getTableName(T entity, StringBuilder sqlBuilder) {
        String tableName = entity.getClass().getName();
        if (entity.getClass().isAnnotationPresent(Table.class)) {
            Table table = entity.getClass().getAnnotation(Table.class);
            if (!Objects.equals("", table.value())) {
                tableName = table.value();
            }
        }
        sqlBuilder.append("insert into ").append(tableName).append(" (");
    }
}
