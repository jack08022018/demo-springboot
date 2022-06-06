package com.demo.configuration.datasources;

import com.demo.entity.mydb.UsersEntity;
import org.apache.commons.dbcp2.BasicDataSource;
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
    @Primary
    @Bean(name = "mydbProperties")
    @ConfigurationProperties("app.datasource.mydb")
    public DataSourceProperties getProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "mydbDataSource")
    @ConfigurationProperties("app.datasource.mydb.configuration")
    public DataSource getDataSource() {
        return getProperties()
                .initializeDataSourceBuilder()
                .type(BasicDataSource.class)
                .build();
    }

    @Primary
    @Bean(name = "mydbEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(getDataSource())
                .packages(UsersEntity.class)
                .build();
    }

    @Primary
    @Bean(name = "mydbTransactionManager")
    public PlatformTransactionManager getTransactionManager(
            final @Qualifier("mydbEntityManagerFactory")
            LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory.getObject());
    }
}
