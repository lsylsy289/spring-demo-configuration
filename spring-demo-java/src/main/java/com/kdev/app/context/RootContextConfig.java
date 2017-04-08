package com.kdev.app.context;

import javax.annotation.Resource;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.kdev.app" })
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
public class RootContextConfig {
	@Resource
    private Environment env;

    @Bean
    public DataSource dataSource() {
        DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driver"));
        dataSource.setUsername(env.getProperty("jdbc.username"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        dataSource.setConnectionProperties("true");
        dataSource.setTestOnReturn(true);
        dataSource.setTestWhileIdle(true);
        dataSource.setRemoveAbandoned(true);
        dataSource.setRemoveAbandonedTimeout(30);
        dataSource.setLogAbandoned(true);
        dataSource.setValidationQuery("SELECT 1");
        return dataSource;
    }
	
    @Bean
    public PlatformTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dataSource());
    }
}
