package com.klm.travel_casestudy.controller;

import com.klm.travel_casestudy.Service.AsyncService;
import com.klm.travel_casestudy.domain.Airport;
import com.klm.travel_casestudy.domain.Fare;
import com.klm.travel_casestudy.domain.Location;
import com.klm.travel_casestudy.domain.Token;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@CrossOrigin(origins = "*")
@RestController
@Slf4j
public class AirportController {

    @Autowired
    private AsyncService service;

    @GetMapping(value = "/airports", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, params = "term")
    public Airport getAirports(@RequestParam("term") String term) {
        Airport airports = null;
        try {
            airports = service.getAirportsByTerm(term, buildAuth(service.fetchToken())).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return airports;
    }

    @GetMapping(value = "/fares/{origin}/{destination}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Fare getFare(@PathVariable("origin") String origin,
                        @PathVariable("destination") String destination) throws ExecutionException, InterruptedException {
        CompletableFuture<Fare> fare = service.getFare(origin, destination, buildAuth(service.fetchToken()));
        return fare.get();
    }

    @GetMapping(value = "/airports/{code}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Location getAirport(@PathVariable("code") String code) throws ExecutionException, InterruptedException {
        CompletableFuture<Location> airportByCode = service.getAirportByCode(code, buildAuth(service.fetchToken()));
        return airportByCode.get();
    }

    private static String buildAuth(Token token) {
        return String.format("%s %s", token.getTokenType(), token.getAccessToken());
    }

}
