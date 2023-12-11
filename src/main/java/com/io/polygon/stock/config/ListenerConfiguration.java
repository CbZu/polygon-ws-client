package com.io.polygon.stock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class ListenerConfiguration {
    @Bean
    TaskExecutor taskExecutor() {
        return new ThreadPoolTaskExecutor();
    }
}
