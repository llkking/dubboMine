package com.hosjoy.dubbo.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

import com.hosjoy.dubbo.provider.listener.WebThreadListener;

/**
 * Spring Boot 应用启动类
 *
 * Created by llk.
 */
@SpringBootApplication
@EnableAutoConfiguration//启用自动配置
@ImportResource({"classpath:spring-dubbo.xml"})
public class AppAplication {
    public static void main(String[] args) {
        // SpringBoot 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
        SpringApplication.run(AppAplication.class,args);
    }
    
    /*@Bean
    public ServletRegistrationBean jerseyServlet() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new ServletContainer(), "/*");
        registration.addInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, JerseyConfig.class.getName());
        return registration;
    }*/
    
    @Bean
    public WebThreadListener excuteBootstrapListener() {
       return new WebThreadListener();
    }
}
