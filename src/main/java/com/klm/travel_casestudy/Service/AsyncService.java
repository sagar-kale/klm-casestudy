package com.klm.travel_casestudy.Service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.client.RestOperations;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public interface AsyncService {
    // implementation of this method should be annotated with @Async
    @Async
    <T> CompletableFuture<T> getAsynchronousResults(String resourceUrl,
                                                    Class<T> resultType, RestOperations restTemplate);

    default <T> T getForObject(String resourceUrl, Class<T> responseType,
                               RestOperations restTemplate) {
        return restTemplate.getForObject(resourceUrl, responseType);
    }
}
