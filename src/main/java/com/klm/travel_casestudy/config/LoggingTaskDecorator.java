package com.klm.travel_casestudy.config;

import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;

import java.util.Map;

public class LoggingTaskDecorator implements TaskDecorator {
    @Override
    public Runnable decorate(Runnable runnable) {
        Map<String, String> webThreadContext = MDC.getCopyOfContextMap();
        return () -> {
            try {
                MDC.setContextMap(webThreadContext);
                runnable.run();
            } finally {
                MDC.clear();
            }

        };
    }
}
