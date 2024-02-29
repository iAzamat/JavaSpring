package ru.gb.homework9.storeutils.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyMetrics {
    private final Counter aboutCounter;

    @Autowired
    public MyMetrics(MeterRegistry meterRegistry) {
        this.aboutCounter = meterRegistry.counter("store-app.about.requests");
    }

    public void incrementAboutCounter() {
        aboutCounter.increment();
    }
}
