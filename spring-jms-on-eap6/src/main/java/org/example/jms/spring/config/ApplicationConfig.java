package org.example.jms.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

@Configuration
@ComponentScan("org.example.jms.spring.service")
public class ApplicationConfig {
    @Bean
    public PlatformTransactionManager jtaTransactionManager() {
        return new JtaTransactionManager();
    }
}