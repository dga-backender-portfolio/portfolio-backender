package org.dga.backend.portfolio.async.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.weaving.DefaultContextLoadTimeWeaver;
import org.springframework.instrument.classloading.LoadTimeWeaver;

import java.lang.instrument.ClassFileTransformer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@EnableAspectJAutoProxy
public class AsyncConfig {
    @Bean(name = "SomeThreads", destroyMethod = "shutdown")
    public ExecutorService executorService(@Value("${mymicro.nThreads}") int nThreads) {
        return Executors.newFixedThreadPool(nThreads);
    }


}
