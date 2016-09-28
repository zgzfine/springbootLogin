package com.demo.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSourceFactory;


@Configuration
public class DataSourceConfig {

	private Logger logger = LoggerFactory.getLogger(DataSourceConfig.class.getName());
	
	@Autowired
	private Environment env;
	
	//配置数据源
	@Bean
	public DataSource dataSource() {
		Map<String, String> configMap = new HashMap<String, String>();
		configMap.put("driverClassName", env.getProperty("jdbc.driverClassName"));
		configMap.put("url", env.getProperty("jdbc.url"));
		configMap.put("username", env.getProperty("jdbc.username"));
		configMap.put("password", env.getProperty("jdbc.password"));
		configMap.put("filters", env.getProperty("jdbc.filters"));
		configMap.put("initialSize", env.getProperty("jdbc.initialSize"));
		configMap.put("maxActive", env.getProperty("jdbc.maxActive"));
		configMap.put("maxWait", env.getProperty("jdbc.maxWait"));
		configMap.put("timeBetweenEvictionRunsMillis", env.getProperty("jdbc.timeBetweenEvictionRunsMillis"));
		configMap.put("minEvictableIdleTimeMillis", env.getProperty("jdbc.minEvictableIdleTimeMillis"));
		configMap.put("validationQuery", env.getProperty("jdbc.validationQuery"));
		configMap.put("testWhileIdle", env.getProperty("jdbc.testWhileIdle"));
		configMap.put("testOnBorrow", env.getProperty("jdbc.testOnBorrow"));
		configMap.put("testOnReturn", env.getProperty("jdbc.testOnReturn"));
		configMap.put("poolPreparedStatements", env.getProperty("jdbc.poolPreparedStatements"));
		configMap.put("maxPoolPreparedStatementPerConnectionSize", env.getProperty("jdbc.maxPoolPreparedStatementPerConnectionSize"));
		try {
			logger.debug("--->初始化DruidData");
			System.out.println("--->初始化DruidData");
			return DruidDataSourceFactory.createDataSource(configMap);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		return null;
	}
	
	//事务管理bean
	@Bean(name = "dataSourceTransactionManager")
	@ConditionalOnMissingBean
	public DataSourceTransactionManager transactionManager() {
		logger.debug("初始化 DataSourceTransactionManager事务管理");
		System.out.println("初始化 DataSourceTransactionManager事务管理");
		return new DataSourceTransactionManager(dataSource());
	}
	
	@Bean(name = "sqlSessionFactory")
	@ConditionalOnMissingBean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {

		System.out.println("*****************  初始化 SqlSessionFactory");
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setTypeAliasesPackage("com.demo");

		Properties properties = new Properties();
		properties.put("dialect", "mysql");
		
		sessionFactory.setConfigurationProperties(properties);
		
		return sessionFactory.getObject();
	}
}
