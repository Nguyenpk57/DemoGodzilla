package com.ntt.godzilla.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ConfigurationProperties(prefix = "spring.second.datasource")
public class DatabaseSecondConfiguration extends BaseDatabaseConfig {

    @Value("${spring.second.datasource.url}")
    private String url;

    @Value("${spring.second.datasource.username}")
    private String username;

    @Value("${spring.second.datasource.password}")
    private String password;

    @Bean(name = "secondDataSource")
    public DataSource secondDataSource() {
        return readDatabase(url, username, password);

    }

    @Bean(name = "secondSessionFactory")
    public LocalSessionFactoryBean secondSessionFactory() {
        String[] packageToScan = new String[] {"com.ntt.godzilla.entity.entitySecond"};
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(secondDataSource());
        sessionFactory.setPackagesToScan(packageToScan);
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    private final Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty(
                "hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        return hibernateProperties;
    }

}