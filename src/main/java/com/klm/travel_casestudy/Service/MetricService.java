package com.klm.travel_casestudy.Service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class MetricService {

    private ConcurrentMap<Character, Integer> statusMetric;

    public MetricService() {
        statusMetric = new ConcurrentHashMap<>();
    }

    public void increaseCount(String request, int status) {
        String stat = String.valueOf(status);
        Character c = stat.charAt(0);
        Integer statusCount = statusMetric.get(c);
        if (statusCount == null) {
            statusMetric.put(c, 1);
        } else {
            statusMetric.put(c, statusCount + 1);
        }
    }

    public Map<Character, Integer> getStatusMetric() {
        return statusMetric;
    }
}