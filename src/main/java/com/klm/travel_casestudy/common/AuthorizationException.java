package com.klm.travel_casestudy.common;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@ToString
@Getter
public class AuthorizationException extends RuntimeException {
    private final HttpStatus code;

    public AuthorizationException(HttpStatus code, String msg) {
        super(msg);
        this.code = code;
    }
}
