package org.ryz2593.orm.util;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author ryz2593
 * @date 2019/4/23
 * @desc
 */
public class PropertiesUtil extends Properties {
    public static final String CONFIG_LOCATION = "jdbc.properties";
    private static PropertiesUtil util = new PropertiesUtil();

    private PropertiesUtil() {
        try (
                InputStream in = this.getClass().getClassLoader().getResourceAsStream(CONFIG_LOCATION)
        ) {
            this.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PropertiesUtil getInstance() {
        return util;
    }
}
