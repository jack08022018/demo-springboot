package com.demo.configuration.datasources;

import com.demo.dto.mydb.UsersEntity;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.demo.repository.myDB",
            entityManagerFactoryRef = "mydbEntityManagerFactory",
            transactionManagerRef= "mydbTransactionManager")
public class MydbDataSourceConfig {
    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.mydb")
    public DataSourceProperties mydbProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.mydb.configuration")
    public DataSource mydbDataSource() {
        return mydbProperties().initializeDataSourceBuilder()
                .type(BasicDataSource.class)
                .build();
    }

    @Primary
    @Bean(name = "mydbEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean mydbEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(mydbDataSource())
                .packages(UsersEntity.class)
                .build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager mydbTransactionManager(
            final @Qualifier("mydbEntityManagerFactory")
            LocalContainerEntityManagerFactoryBean mydbEntityManagerFactory) {
        return new JpaTransactionManager(mydbEntityManagerFactory.getObject());
    }
}
