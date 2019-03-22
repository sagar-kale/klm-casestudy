package com.klm.travel_casestudy.controller;

import com.klm.travel_casestudy.Service.AsyncService;
import com.klm.travel_casestudy.domain.Airport;
import com.klm.travel_casestudy.domain.Fare;
import com.klm.travel_casestudy.domain.Location;
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
    public Airport getAirports(@RequestHeader(value = "Authorization") String auth, @RequestParam("term") String term) {
        log.info("Authrization value :: " + auth);
        Airport airports = null;
        try {
            airports = service.getAirportsByTerm(term, auth).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return airports;
    }

    @GetMapping(value = "/fares/{origin}/{destination}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Fare getFare(@RequestHeader(value = "Authorization") String auth, @PathVariable("origin") String origin,
                        @PathVariable("destination") String destination) throws ExecutionException, InterruptedException {
        log.info("token :::: "+auth);
        CompletableFuture<Fare> fare = service.getFare(origin, destination, auth);
        return fare.get();
    }

    @GetMapping(value = "/airports/{code}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Location getAirport(@RequestHeader(value = "Authorization") String auth, @PathVariable("code") String code) throws ExecutionException, InterruptedException {
        CompletableFuture<Location> airportByCode = service.getAirportByCode(code, auth);
        return airportByCode.get();
    }



/*
    @GetMapping(value = "/testAsynch")
    public void testAsynch() throws InterruptedException, ExecutionException {
        log.info("testAsynch Start");

        CompletableFuture<Location> airports = service.getAirportByCode("AMS");
        CompletableFuture<Location> employeeName = service.getAirportsByTerm("Amsterdam");
        CompletableFuture<Fare> employeePhone = service.getFare();

        // Wait until they are all done
        CompletableFuture.allOf(employeeAddress, employeeName, employeePhone).join();

        log.info("EmployeeAddress--> " + employeeAddress.get());
        log.info("EmployeeName--> " + employeeName.get());
        log.info("EmployeePhone--> " + employeePhone.get());
    }
*/

}
