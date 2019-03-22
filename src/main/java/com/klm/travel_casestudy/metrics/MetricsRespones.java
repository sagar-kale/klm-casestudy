package com.klm.travel_casestudy.metrics;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

@JsonSerialize
@Getter
@Setter
public class MetricsRespones {
    private Double totalNumberOfRequests;
    private Integer totalNumberOfOKRes;
    private Integer totalNumberOf4XXRes;
    private Integer totalNumberOf5XXRes;
    private Double avgResTime;
    private Double maxResTime;
}
