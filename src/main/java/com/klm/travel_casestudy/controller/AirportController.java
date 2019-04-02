package com.klm.travel_casestudy.controller;

import com.klm.travel_casestudy.Service.AsyncService;
import com.klm.travel_casestudy.common.TravelAppException;
import com.klm.travel_casestudy.domain.Airport;
import com.klm.travel_casestudy.domain.Endpoint;
import com.klm.travel_casestudy.domain.Fare;
import com.klm.travel_casestudy.domain.Location;
import com.klm.travel_casestudy.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@CrossOrigin(origins = "*")
@RestController
@Slf4j
public class AirportController {

    @Autowired
    private AsyncService asyncService;
    @Autowired
    private OAuth2RestOperations restTemplate;

    @Autowired
    private Endpoint endpoint;
    @Autowired
    private Utils utils;

    @GetMapping(value = "/airports", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, params = "term")
    public Airport getAirports(@RequestParam("term") String term) throws ExecutionException, InterruptedException {
        log.info("under airport by term method");
        Map map = null;
        try {
            CompletableFuture<Map> future = asyncService.getAsynchronousResults(endpoint.getAirportUrlByTerm() + term, Map.class, restTemplate);
            map = future.get();
        } catch (TravelAppException e) {
            log.error("Error Occurred ::: {} {}", e.getCode(), e.getMessage());
        }
        if (null == map)
            return new Airport();
        return utils.convertTOEntity(map, Airport.class);
    }

    @GetMapping(value = "/fares/{origin}/{destination}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Fare getFare(@PathVariable("origin") String origin,
                        @PathVariable("destination") String destination) throws ExecutionException, InterruptedException {
        CompletableFuture<Fare> asynchronousResults = null;
        try {
            asynchronousResults = asyncService.getAsynchronousResults(endpoint.getFareUrl() + origin + endpoint.getSlash() + destination, Fare.class, restTemplate);
        } catch (TravelAppException e) {
            log.error("Error Occurred ::: {} {}", e.getCode(), e.getMessage());
        }
        return asynchronousResults.get() != null ? asynchronousResults.get() : new Fare();
    }

    @GetMapping(value = "/airports/{code}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Location getAirport(@PathVariable("code") String code) throws ExecutionException, InterruptedException {
        String result = null;
        try {
            CompletableFuture<String> asynchronousResults = asyncService.getAsynchronousResults(endpoint.getAirportByCode() + code, String.class, restTemplate);
            result = asynchronousResults.get();
        } catch (TravelAppException e) {
            log.error("Error Occurred ::: {} {}", e.getCode(), e.getMessage());
        }
        if (null == result)
            return new Location();
        Map map = new HashMap();
        map.put("airport", result);
        return utils.convertTOEntity(map, Location.class);
    }

    @GetMapping(value = "/airports", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Airport getAllAirports(@RequestParam(value = "page",defaultValue = "1") String page, @RequestParam(value = "size",defaultValue = "25") String size) throws ExecutionException, InterruptedException, TravelAppException {
        log.info("fetching all airports");
        Map map = null;
        String url = String.format(endpoint.getAirportsUrl(), page, size);
        log.info("Pageable url {}", url);
        try {
            CompletableFuture<Map> future = asyncService.getAsynchronousResults(url, Map.class, restTemplate);
            map = future.get();
        } catch (TravelAppException e) {
            log.error("Error Occurred ::: {} {}", e.getCode(), e.getMessage());
        }
        if (null == map)
            return new Airport();
        return utils.convertTOEntity(map, Airport.class);
    }


}
