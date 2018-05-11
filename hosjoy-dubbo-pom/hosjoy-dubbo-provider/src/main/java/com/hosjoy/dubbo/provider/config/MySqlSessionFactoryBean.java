package com.hosjoy.dubbo.provider.config;

import java.io.InputStream;
import java.util.Properties;

import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.core.Application;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import com.hosjoy.dubbo.provider.util.PagePluginHj;

/**
 * MyBatis基础配置
 *
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass=true)
public class MySqlSessionFactoryBean implements TransactionManagementConfigurer{
	
	private final Logger logger = LoggerFactory.getLogger(MySqlSessionFactoryBean.class); 
	
    @Autowired
    DataSource dataSource;
    
    private String url;
    private String driverName;
    private String userName;
    private String password;
	
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean() {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage("com.hosjoy.core.model");

        //分页插件
        PagePluginHj pageHelper = new PagePluginHj();
        Properties properties = new Properties();
        properties.setProperty("dialect", "mysql");
        properties.setProperty("pageSqlId", ".*ListPage.*");
        pageHelper.setProperties(properties);

        //添加插件
        bean.setPlugins(new Interceptor[]{pageHelper});
        
        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            bean.setMapperLocations(resolver.getResources("classpath*:com/hosjoy/core/mapper/*Mapper.xml"));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    
    @Bean
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
    
	@Bean(destroyMethod="")
	public DataSource jndiDataSource() throws IllegalArgumentException, NamingException {
		Properties pro = new Properties();
		InputStream input =Application.class.getClassLoader().getResourceAsStream("application.properties");
		try {
			pro.load(input);
		} catch (Exception e) {
			logger.error("STARTUP ERROR WHEN LOADING application.properties", e);
		}
		logger.info(">>>>>>>>>> CURRENT ENV IS "+String.valueOf(pro.get("profile"))+"<<<<<<<<<<");
		
		String debug = String.valueOf(pro.get("dev.debug"));
		// test prd  dev.debug=0 ; dev dev.debug=1
		if("0".equals(debug)){
			JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
			bean.setJndiName(String.valueOf(pro.get("spring.datasource.jndi-name")));
			bean.setProxyInterface(DataSource.class);
			bean.setLookupOnStartup(false);
			bean.afterPropertiesSet();
			dataSource=(DataSource)bean.getObject();
		}else{
			driverName = String.valueOf(pro.get("spring.datasource.driver-class-name"));
			url = String.valueOf(pro.get("spring.datasource.url"));
			userName = String.valueOf(pro.get("spring.datasource.username"));
			password = String.valueOf(pro.get("spring.datasource.password"));
			DataSourceBuilder factory = DataSourceBuilder  
                .create(Application.class.getClassLoader())  
                .driverClassName(driverName)  
                .url(url).username(userName)  
                .password(password);  
			dataSource= factory.build();
		}
		
		return  dataSource;
	}
    
}
