package com.lesson12.block1.computerstore;

import com.lesson12.block1.computerstore.domain.MyProductFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public MyProductFactory productFactory(){
        return new MyProductFactory();
    }
}
