package com.klm.travel_casestudy.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

@JsonSerialize
@Getter
@Setter
public class Fare {
    @JsonProperty("amount")
    private double amount;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("origin")
    private String origin;
    @JsonProperty("destination")
    private String destination;

}
