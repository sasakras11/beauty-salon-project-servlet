package com.salon.dao;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


public class DataSource {


    private static final Logger LOGGER = LoggerFactory.getLogger(DataSource.class);
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    static {
        try {
            Properties properties = new Properties();
            properties.load(new FileReader("src/main/resources/db.properties"));

            config.setJdbcUrl(properties.getProperty("db.url"));
            config.setUsername(properties.getProperty("db.username"));
            config.setPassword(properties.getProperty("db.password"));
            config.setDriverClassName(properties.getProperty("db.driver"));
            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "250");
            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
            config.setMaximumPoolSize(120000);

            ds = new HikariDataSource(config);

        } catch (IOException e) {
            LOGGER.warn("cannot read properties file");
        }
    }

    private DataSource() {
    }

    public static void setNewProperties(String prop) {
        try {
            Properties properties = new Properties();
            properties.load(new FileReader(prop));
            config.setJdbcUrl(properties.getProperty("db.url"));
            config.setUsername(properties.getProperty("db.username"));
            config.setPassword(properties.getProperty("db.password"));
            config.setDriverClassName(properties.getProperty("db.driver"));
            config.setMaximumPoolSize(50);
            config.setLeakDetectionThreshold(60000);
            config.setConnectionTimeout(30000);

            ds = new HikariDataSource(config);

        } catch (IOException e) {
            LOGGER.warn("cannot read properties file");
        }
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}