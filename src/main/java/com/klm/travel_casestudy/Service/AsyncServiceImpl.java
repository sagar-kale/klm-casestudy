package com.klm.travel_casestudy.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class AsyncServiceImpl implements AsyncService {

    @Override
    @Async
    public <T> CompletableFuture<T> getAsynchronousResults(String resourceUrl, Class<T> resultType, RestOperations restTemplate) {
        log.info("executing async method");
        return CompletableFuture.completedFuture(getForObject(resourceUrl, resultType,
                restTemplate));
    }
}