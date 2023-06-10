package com.jin.crawling.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@TestConfiguration
public class ThreadTestConfig {

    @Value("${thread.core-pool-size}")
    private int corePoolSize;

    @Value("${thread.max-pool-size}")
    private int maxPoolSize;

    @Bean
    public TaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setThreadNamePrefix("crawling_thread");
        executor.initialize();

        return executor;
    }
}
