package com.hosjoy.dubbo.provider.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 线程池配置
 * @author Administrator
 *
 */
@Configuration
public class ThreadPoolConfig {
	
	private final Logger logger = LoggerFactory.getLogger(ThreadPoolConfig.class);
	
	@Value("${tp.corePoolSize}")
    private int corePoolSize;//线程池维护线程的最少数量

    @Value("${tp.maxPoolSize}")
    private int maxPoolSize;//线程池维护线程的最大数量

    @Value("${tp.keepAlive}")
    private int keepAlive;//允许的空闲时间
	
    /*@Bean
    public Executor myExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix("mqExecutor-");
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务  
        // CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行  
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy()); //对拒绝task的处理策略
        executor.setKeepAliveSeconds(keepAlive);
        executor.initialize();
        logger.info(">>>>>>>>>>ThreadPoolTaskExecutor init success<<<<<<<<<<<");
        return executor;
    }*/
    
    @Bean
    public ExecutorService getThreadPool(){
    	
    	logger.info(">>>>>>>>>>ThreadPool init<<<<<<<<<<<");
    	return new ThreadPoolExecutor(corePoolSize, maxPoolSize,
    			keepAlive, TimeUnit.SECONDS,
    			new LinkedBlockingQueue<Runnable>());
    }
    //调度线程池
   /* @Bean
    public ScheduledExecutorService getScheduledThreadPool(){
    	logger.info(">>>>>>>>>>ScheduledThreadPool init<<<<<<<<<<<");
    	ScheduledExecutorService pool = Executors.newScheduledThreadPool(corePoolSize);
    	return pool;
    }*/

}
