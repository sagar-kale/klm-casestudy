package com.klm.travel_casestudy.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Component
@PropertySource("classpath:endpoints.properties")
@ConfigurationProperties
@Setter
@Getter
public class Endpoint {
    private String airportUrlByTerm;
    private String airportsUrl;
    private String tokenUrl;
    private String clientId;
    private String secret;
    private String grantType;
    private String airportByCode;
    private String fareUrl;
    private String slash;
}
