package org.dga.backend.portfolio.async.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class AsyncConfig {
    @Bean(name = "SomeThreads", destroyMethod = "shutdown")
    public ExecutorService executorService(@Value("${mymicro.nThreads}") int nThreads) {
        return Executors.newFixedThreadPool(nThreads);
    }


}
