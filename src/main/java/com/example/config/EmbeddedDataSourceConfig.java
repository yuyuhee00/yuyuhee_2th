package com.example.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class EmbeddedDataSourceConfig {
    private static Logger logger = LoggerFactory.getLogger(EmbeddedDataSourceConfig.class);

    @Bean
    public DataSource dataSource() {
        try {
            var dbBuilder = new EmbeddedDatabaseBuilder();
            return dbBuilder
                    .setType(EmbeddedDatabaseType.H2)
                    .addScripts("classpath:h2/create-schema.sql", "classpath:h2/test-data.sql").build();
        } catch (Exception e) {
            logger.error("Embedded Datasource bean cannot be created!");
        }
        return null;
    }
}
