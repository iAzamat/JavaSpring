package ru.gb.performance.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import jakarta.annotation.PostConstruct;

@Slf4j
@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = "app.common.performance")
public class PerformanceProperties {
    /**
     * to enable common logging aop on service layer
     */
    private boolean enabled;
    private String level;

    @PostConstruct
    void init() {
        log.info("Performance properties initialized: {}", this);
    }
}
