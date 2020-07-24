package com.webank.wecube.demo.springboot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MonitorLogger {
    private static final Logger logger = LoggerFactory.getLogger("monitorLogger");
    private Long metric_1 = 0L;
    private Random random1 = new Random();
    private Random random2 = new Random();
    private Random random3 = new Random();

    @Scheduled(fixedRate = 5000L)
    public void printBusinessMetrics() {
        try {
            String logMessage = new ObjectMapper().writeValueAsString(generateMetrics());
            logger.info(logMessage);
        } catch (JsonProcessingException e) {
            logger.error("Failed to print log message for business metrics.", e);
        }
    }

    private Map<String, String> generateMetrics() {
        return new LinkedHashMap<String, String>() {{
            metric_1 += random1.nextInt(10);
            put("metric_1", String.valueOf(metric_1));
            put("metric_2", String.valueOf(random2.nextFloat()));
            put("metric_3", String.valueOf(random3.nextGaussian()));
        }};
    }
}
