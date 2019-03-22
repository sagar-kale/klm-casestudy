package com.klm.travel_casestudy.metrics;

import com.klm.travel_casestudy.Service.MetricService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@Slf4j
@RequestMapping("/metrics")
public class MetrixController {
    @Autowired
    MetricsEndpoint metricsEndpoint;
    @Autowired
    MetricService metricService;

    Map<String, Integer> statusCounter = new HashMap<>();

    @GetMapping("/total/requests")
    public MetricsRespones ggetData() {
        MetricsRespones metricsRespones = new MetricsRespones();
        log.info("under matrixxxx");
        MetricsEndpoint.MetricResponse metric = getMatricWithoutTag();

        if (metric == null)
            return metricsRespones;
        List<MetricsEndpoint.Sample> measurements = metric.getMeasurements();
        Map<String, Double> map = new LinkedHashMap<>();
        for (MetricsEndpoint.Sample measurement : measurements) {
            map.put(measurement.getStatistic().toString(), measurement.getValue());
        }

        metricsRespones.setTotalNumberOfRequests(map.get("COUNT"));
        metricsRespones.setMaxResTime(map.get("MAX"));
        metricsRespones.setAvgResTime(map.get("TOTAL_TIME"));
        metricsRespones.setTotalNumberOfOKRes(getData(2));
        metricsRespones.setTotalNumberOf5XXRes(getData(5));
        metricsRespones.setTotalNumberOf4XXRes(getData(4));

        return metricsRespones;
    }


    private MetricsEndpoint.MetricResponse getMatricWithoutTag() {
        return metricsEndpoint.metric("http.server.requests", null);
    }

    private MetricsEndpoint.MetricResponse getMatricWithTag(String tag) {
        return metricsEndpoint.metric("http.server.requests", Arrays.asList(tag));
    }

    private Integer getData(Integer status) {
        return metricService.getStatusMetric().getOrDefault(status.toString().charAt(0), 0);


    }
}
