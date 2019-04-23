package org.ryz2593.orm.datasource.impl;

import org.ryz2593.orm.datasource.DataSource;
import org.ryz2593.orm.datasource.DataSourceConstant;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

/**
 * @author ryz2593
 * @date 2019/4/23
 * @desc
 */
public class DataSourceImpl implements DataSource, DataSourceConstant {

    static {
        try {
            //注册驱动
            Driver driver = (Driver) Class.forName(DRIVER_CLASS).newInstance();
            DriverManager.registerDriver(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getDataSource() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
