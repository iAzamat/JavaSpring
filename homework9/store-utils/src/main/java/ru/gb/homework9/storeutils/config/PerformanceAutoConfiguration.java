package ru.gb.homework9.storeutils.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.gb.homework9.storeutils.aop.PerformanceAspect;


@Slf4j
@Configuration
@EnableConfigurationProperties(PerformanceProperties.class)
@ConditionalOnClass(PerformanceProperties.class)
@ConditionalOnProperty(prefix = "app.common.performance", name = "enabled", havingValue = "true")
public class PerformanceAutoConfiguration {

    @PostConstruct
    void init() {
        log.info("Performance AutoConfiguration initialized");
    }
    @Bean
    @ConditionalOnMissingBean
    public PerformanceAspect performanceAspect() {
        return new PerformanceAspect();
    }
}
