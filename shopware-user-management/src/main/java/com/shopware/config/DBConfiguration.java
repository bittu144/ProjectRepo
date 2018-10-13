package com.shopware.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
@EnableTransactionManagement
public class DBConfiguration {

	@Value("${entitymanager.packagesToScan}")
	private String packageToSkan;

	@Bean
	public DataSource dataSource3() {
		return new HikariDataSource(hikariCp3());
	}

	@Bean
	public HikariConfig hikariCp3() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName("com.mysql.jdbc.Driver");
		hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/vraindb");
		hikariConfig.setUsername("root");
		hikariConfig.setPassword("Vrainsingh26@");

		hikariConfig.setPoolName("mfsAuthHikariCP");
		hikariConfig.setMaximumPoolSize(10);
		hikariConfig.setLeakDetectionThreshold(15000);
		Properties dsProperties = new Properties();
		dsProperties.setProperty("dataSource.cachePrepStmts", "true");
		dsProperties.setProperty("dataSource.prepStmtCacheSize", "250");
		dsProperties.setProperty("dataSource.prepStmtCacheSqlLimit", "2048");
		dsProperties.setProperty("dataSource.useServerPrepStmts", "true");

		hikariConfig.setDataSourceProperties(dsProperties);
		return hikariConfig;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource3());
		sessionFactory.setPackagesToScan(packageToSkan);
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		hibernateProperties.put("hibernate.show_sql", true);
		hibernateProperties.put("hibernate.hbm2ddl.auto", "create");

		sessionFactory.setHibernateProperties(hibernateProperties);
		return sessionFactory;
	}

	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}

}