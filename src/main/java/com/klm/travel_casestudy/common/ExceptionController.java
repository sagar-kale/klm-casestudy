package com.klm.travel_casestudy.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

@Slf4j
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Throwable.class)
    public HttpEntity handleGlobalException(Throwable t) {
        log.error("Unable to process request.", t);
        return new ResponseEntity(SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(TravelAppException.class)
    public HttpEntity handleGlobalException(TravelAppException t) {
        return new ResponseEntity(t.getMessage(), t.getCode());
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public HttpEntity handleGlobalException(HttpServerErrorException e) {
        return new ResponseEntity(e.getStatusCode());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public HttpEntity handleBadRequest() {
        return new ResponseEntity(BAD_REQUEST);
    }


}
