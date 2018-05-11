package com.hosjoy.dubbo.provider.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

import com.alibaba.dubbo.remoting.http.servlet.BootstrapListener;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import com.alibaba.dubbo.config.ProtocolConfig;


/**
 * 监听tomcat
 * 2018-03-26
 */
public class WebThreadListener extends BootstrapListener {

	private final Logger logger = LoggerFactory.getLogger(WebThreadListener.class);
	
	// 关闭tomcat前关闭线程
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		super.contextDestroyed(arg0);
		logger.info(">>>>>>>> hosjoy dubbo threadListener tomcat closed <<<<<<<<<");
		//关闭dubbo进程
		ProtocolConfig.destroyAll();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		super.contextInitialized(arg0);
		logger.info(">>>>>>>> hosjoy dubbo threadListener tomcat initialized <<<<<<<<<");
	}
}