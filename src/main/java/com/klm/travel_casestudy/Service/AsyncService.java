package com.klm.travel_casestudy.Service;

import com.google.gson.Gson;
import com.klm.travel_casestudy.domain.Airport;
import com.klm.travel_casestudy.domain.Fare;
import com.klm.travel_casestudy.domain.Location;
import com.klm.travel_casestudy.domain.Token;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@PropertySource("classpath:endpoints.properties")
@ConfigurationProperties
@Setter
public class AsyncService {

    private String airportUrlByTerm;
    private String airportsUrl;
    private String tokenUrl;
    private String clientCred;
    private String grantType;
    private String airportByCode;
    private String fareUrl;
    private String slash;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private Gson gson;


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    Gson gson() {
        return new Gson();
    }

    HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> httpEntity = null;

    MappingJackson2HttpMessageConverter fetchMsgConvertor() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        // Note: here we are making this converter to process any kind of response,
        // not only application/*json, which is the default behaviour
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.valueOf("")));
        return converter;
    }

    HttpEntity<String> setHeaders(String auth) {
        log.info("auth length:: ::  :: " + auth.length());
        // auth = auth.replaceAll("\"", "").trim();
        //    if (!auth.equalsIgnoreCase("Bearer NA") && !auth.equalsIgnoreCase("Bearer null") && !auth.equalsIgnoreCase("bearer"))
        if (auth.length() == 43)
            headers.add("Authorization", auth);
        return httpEntity = new HttpEntity<>(headers);
    }

    @Async("asyncExecutor")
    public CompletableFuture<Airport> getAirportsByTerm(String term, String auth) throws InterruptedException {
        //   restTemplate.getMessageConverters().add(fetchMsgConvertor());
        HttpEntity<String> stringHttpEntity = setHeaders(auth);
        ResponseEntity<Map> airportMap = null;
        try {
            airportMap = restTemplate.exchange(airportUrlByTerm + term, HttpMethod.GET, stringHttpEntity, Map.class);
        } catch (Exception e) {
            log.error("Error Ocuured while accessing airport", e.getMessage());
        }
        if (null == airportMap)
            return CompletableFuture.completedFuture(new Airport());
        log.info("Airport data, {}", airportMap.getBody());
        //Map map = gson.fromJson(airportData.getBody(), Map.class);
        String embedded = gson.toJson(airportMap.getBody().get("_embedded"));
        Airport airport = gson.fromJson(embedded, Airport.class);
        log.info("Airport data, {}", airport.getLocations());
        log.info("getAirportByTerm end");
        return CompletableFuture.completedFuture(airport);
    }

    @Async("asyncExecutor")
    public CompletableFuture<Location> getAirportByCode(String code, String auth) throws InterruptedException {
        log.info("getAirport by code  starts");
        HttpEntity<String> stringHttpEntity = setHeaders(auth);
        ResponseEntity<String> airportData = restTemplate.exchange(airportByCode + code, HttpMethod.GET, stringHttpEntity, String.class);
        log.info("Airport data, {}", airportData.getBody());
        Location airport = gson.fromJson(airportData.getBody(), Location.class);
        log.info("getAirport by code completed");

        return CompletableFuture.completedFuture(airport);
    }

    @Async("asyncExecutor")
    public CompletableFuture<Fare> getFare(String origin, String dest, String auth) {
        log.info("getFare starts");
        HttpEntity<String> stringHttpEntity = setHeaders(auth);
        ResponseEntity<Fare> fareData = restTemplate.exchange(fareUrl + origin + slash + dest, HttpMethod.GET, stringHttpEntity, Fare.class);
        log.info("getFare completed");
        return CompletableFuture.completedFuture(fareData.getBody());
    }

    public Token fetchToken() {
        headers.add("Authorization", "Basic " + Base64.getEncoder().encodeToString(clientCred.getBytes()));
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("grant type", grantType);
        multiValueMap.add("grant_type", grantType);
        HttpEntity<MultiValueMap<String, String>> multiValueMapHttpEntity = new HttpEntity<>(multiValueMap, headers);
        Token token = restTemplate.postForObject(tokenUrl, multiValueMapHttpEntity, Token.class);
        log.info("requested token :::" + token);
        return token;
    }
}