package com.klm.travel_casestudy.Service;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import java.util.concurrent.Future;

@Service
@Slf4j
@ConfigurationProperties
@Setter
public class AsyncServiceImpl implements AsyncService {

    @Override
    @Async
    public <T> Future<T> getAsynchronousResults(String resourceUrl, Class<T> resultType, RestOperations restTemplate) {
        return new AsyncResult<>(getForObject(resourceUrl, resultType,
                restTemplate));
    }
}