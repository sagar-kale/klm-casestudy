package com.klm.travel_casestudy.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.net.URI;

@Slf4j
public class ExceptionHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {

        if (clientHttpResponse.getStatusCode() != HttpStatus.OK) {

            log.error("Error Response :::" + clientHttpResponse.getStatusText());
            log.error("Error header:::" + clientHttpResponse.getHeaders().entrySet());
            log.error("status code ::: " + clientHttpResponse.getStatusCode().value());
            //   log.error("status body ::: " + StreamUtils.copyToString(clientHttpResponse.getBody(), Charset.defaultCharset()));
            if (clientHttpResponse.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                return true;
            }
            return true;

        }
        return false;
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {

    }

    @Override
    public void handleError(URI url, HttpMethod method, ClientHttpResponse response) throws IOException {
        if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            log.info("Under Handle error.............");
            log.info("URI:: {}", url);
            log.info("Method::: {}", method);
            log.error("Error Response:::" + response.getStatusText());
            log.error("Error Headers::" + response.getHeaders().entrySet());
            log.error("Error status code ::: " + response.getStatusCode().value());
            throw new AuthorizationException(response.getStatusCode(), response.getStatusText());
        }
        throw new TravelAppException(response.getStatusCode(), response.getStatusText());
    }
}
