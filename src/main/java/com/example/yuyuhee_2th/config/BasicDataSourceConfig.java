package com.example.yuyuhee_2th.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:database.properties")
public class BasicDataSourceConfig {
    private static Logger logger = LoggerFactory.getLogger(BasicDataSourceConfig.class);

    @Value("${maria.jdbc.driver}")
    private String driverClassName;

    @Value("${maria.jdbc.url}")
    private String url;

    @Value("${maria.jdbc.user}")
    private String username;

    @Value("${maria.jdbc.pass}")
    private String password;

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        try {
            var dataSource = new BasicDataSource();
            dataSource.setDriverClassName(this.driverClassName);
            dataSource.setUrl(this.url);
            dataSource.setUsername(this.username);
            dataSource.setPassword(this.password);
            return dataSource;
        } catch (Exception e) {
            logger.error("DBCP DataSource bean cannot be created!", e);
            return null;
        }
    }
}