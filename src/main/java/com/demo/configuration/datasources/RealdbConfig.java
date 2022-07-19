package com.demo.configuration.datasources;

import com.demo.repository.realdb.entity.MMusicEntity;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.demo.repository.realdb",
            entityManagerFactoryRef = "realdbEntityManagerFactory",
            transactionManagerRef= "realdbTransactionManager")
public class RealdbConfig {

    @Bean(name = "realdbProperties")
    @ConfigurationProperties("app.datasource.realdb")
    public DataSourceProperties getProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "realdbDataSource")
    @ConfigurationProperties("app.datasource.realdb.configuration")
    public DataSource getDataSource() {
        return getProperties()
                .initializeDataSourceBuilder()
                .type(BasicDataSource.class)
                .build();
    }

    @Bean(name = "realdbEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(getDataSource())
                .packages(MMusicEntity.class)
                .build();
    }

    @Bean(name = "realdbTransactionManager")
    public PlatformTransactionManager getTransactionManager(
            final @Qualifier("realdbEntityManagerFactory")
            LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory.getObject());
    }
}
