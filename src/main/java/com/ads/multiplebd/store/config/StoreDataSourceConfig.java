package com.ads.multiplebd.store.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.ads.multiplebd.store.repositories",
        entityManagerFactoryRef = "storeEntityManagerFactory",
        transactionManagerRef = "storeTransactionManager"
)
public class StoreDataSourceConfig {
    @Autowired
    private Environment env;

    @Bean
    @ConfigurationProperties(prefix="datasource.store")
    public DataSourceProperties storeDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource storeDataSource() {
        DataSourceProperties storeDataSourceProperties = storeDataSourceProperties();
        return DataSourceBuilder.create()
                .driverClassName(storeDataSourceProperties.getDriverClassName())
                .url(storeDataSourceProperties.getUrl())
                .username(storeDataSourceProperties.getUsername())
                .password(storeDataSourceProperties.getPassword())
                .password(storeDataSourceProperties.getPassword())
                .build();
    }

    @Bean
    public PlatformTransactionManager storeTransactionManager()
    {
        EntityManagerFactory factory = storeEntityManagerFactory().getObject();
        return new JpaTransactionManager(factory);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean storeEntityManagerFactory()
    {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(storeDataSource());
        factory.setPackagesToScan("com.ads.multiplebd.store.models");
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        jpaProperties.put("hibernate.show-sql", env.getProperty("spring.jpa.show-sql"));
        factory.setJpaProperties(jpaProperties);

        return factory;
    }
}
