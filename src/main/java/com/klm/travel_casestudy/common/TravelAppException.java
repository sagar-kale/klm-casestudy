package com.klm.travel_casestudy.common;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@ToString
@Getter
public class TravelAppException extends RuntimeException {
    private final HttpStatus code;

    public TravelAppException(HttpStatus code, String msg) {
        super(msg);
        this.code = code;
    }
}
