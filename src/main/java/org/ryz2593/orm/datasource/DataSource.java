package org.ryz2593.orm.datasource;

import java.sql.Connection;

/**
 * @author ryz2593
 * @date 2019/4/23
 * @desc
 */
public interface DataSource {
    Connection getDataSource();
}
