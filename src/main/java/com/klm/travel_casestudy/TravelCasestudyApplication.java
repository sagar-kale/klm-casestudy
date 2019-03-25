package com.klm.travel_casestudy;

import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@EnableOAuth2Client
@EnableAutoConfiguration
@SpringBootApplication
public class TravelCasestudyApplication {

    @Bean
    Gson gson() {
        return new Gson();
    }

    public static void main(String[] args) {
        SpringApplication.run(TravelCasestudyApplication.class, args);
    }

}
