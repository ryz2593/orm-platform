package org.ryz2593.orm.datasource;

import org.ryz2593.orm.util.PropertiesUtil;

/**
 * @author ryz2593
 * @date 2019/4/23
 * @desc
 */
public interface DataSourceConstant {
    String DRIVER_CLASS = PropertiesUtil.getInstance().getProperty("jdbc.driver_class");
    String URL = PropertiesUtil.getInstance().getProperty("jdbc.url");
    String USER = PropertiesUtil.getInstance().getProperty("jdbc.user");
    String PASSWORD = PropertiesUtil.getInstance().getProperty("jdbc.password");
}
