package com.hosjoy.dubbo.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.ImportResource;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableAutoConfiguration//启用自动配置
@ImportResource({"classpath:spring-dubbo.xml"}) //加入spring的bean的xml文件
public class ConAppAplication implements EmbeddedServletContainerCustomizer
{
    public static void main( String[] args )
    {
    	SpringApplication.run(ConAppAplication.class, args);
    }
    
    @Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		container.setPort(8081);
	}
}
