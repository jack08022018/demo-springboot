package com.demo.configuration.datasources;

import com.demo.dto.sakila.ActorEntity;
import org.apache.commons.dbcp.BasicDataSource;
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
@EnableJpaRepositories(basePackages = "com.demo.repository.sakila",
            entityManagerFactoryRef = "sakilaEntityManagerFactory",
            transactionManagerRef= "sakilaTransactionManager")
public class SakilaDataSourceConfig {
    @Bean
    @ConfigurationProperties("app.datasource.sakila")
    public DataSourceProperties sakilaProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("app.datasource.sakila.configuration")
    public DataSource sakilaDataSource() {
        return sakilaProperties().initializeDataSourceBuilder()
                .type(BasicDataSource.class)
                .build();
    }

    @Bean(name = "sakilaEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean sakilaEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(sakilaDataSource())
                .packages(ActorEntity.class)
                .build();
    }

    @Bean
    public PlatformTransactionManager sakilaTransactionManager(
            final @Qualifier("sakilaEntityManagerFactory")
            LocalContainerEntityManagerFactoryBean sakilaEntityManagerFactory) {
        return new JpaTransactionManager(sakilaEntityManagerFactory.getObject());
    }
}
