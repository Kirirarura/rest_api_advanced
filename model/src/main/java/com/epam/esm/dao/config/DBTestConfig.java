package com.epam.esm.dao.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

/**
 * Class that contains configurations for test database
 */
@Configuration
@ComponentScan("com.epam.esm")
@PropertySource("classpath:dbConnection.properties")
@Profile("test")
public class DBTestConfig {

    @Bean
    public DataSource dataSource(@Value("${db.username}") String user,
                                 @Value("${db.password}") String password,
                                 @Value("${db.driverClassName}") String className,
                                 @Value("${db.jdbcUrl}") String connectionUrl,
                                 @Value("${db.maximumPoolSize}") Integer connectionsNumber) {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUsername(user);
        basicDataSource.setPassword(password);
        basicDataSource.setDriverClassName(className);
        basicDataSource.setUrl(connectionUrl);
        basicDataSource.setMaxTotal(connectionsNumber);

        Resource initData = new ClassPathResource("dbTestCreate.sql");
        Resource fillData = new ClassPathResource("dbTestFill.sql");
        DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initData, fillData);
        DatabasePopulatorUtils.execute(databasePopulator, basicDataSource);
        return basicDataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
