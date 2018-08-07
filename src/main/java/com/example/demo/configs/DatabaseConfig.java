package com.example.demo.configs;


import java.util.Properties;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private LocalContainerEntityManagerFactoryBean entityManagerFactory;

	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	    dataSource.setUrl(environment.getProperty("spring.datasource.url"));
	    dataSource.setUsername(environment.getProperty("spring.datasource.username"));
	    dataSource.setPassword(environment.getProperty("spring.datasource.password"));
	    return (DataSource) dataSource;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactory =
				new LocalContainerEntityManagerFactoryBean();
		
		entityManagerFactory.setDataSource(dataSource());
		
		entityManagerFactory.setPackagesToScan(environment.getProperty("entitymanager.packagesToScan"));
		
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	    entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
	    
	    entityManagerFactory.setBeanName("entityManager");
	    // Hibernate properties
	    Properties additionalProperties = new Properties();
	    additionalProperties.put( "hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
	    additionalProperties.put( "hibernate.show_sql", "true");
	    additionalProperties.put( "hibernate.hbm2ddl.auto","update");
	    entityManagerFactory.setJpaProperties(additionalProperties);
	    
	    return entityManagerFactory;
	}
	
	@Bean
	public JpaTransactionManager transactionManager() {
	    JpaTransactionManager transactionManager = 
	        new JpaTransactionManager();
	    transactionManager.setEntityManagerFactory(
	        entityManagerFactory.getObject());
	    return transactionManager;
	  }
	
	 @Bean
	  public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
	    return new PersistenceExceptionTranslationPostProcessor();
	  }
}
