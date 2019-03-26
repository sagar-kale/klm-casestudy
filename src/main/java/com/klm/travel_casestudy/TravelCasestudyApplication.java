package com.klm.travel_casestudy;

import com.klm.travel_casestudy.filter.TraceFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@EnableOAuth2Client
@EnableAutoConfiguration
@SpringBootApplication
public class TravelCasestudyApplication {

    @Bean
    FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean traceFilterFilterRegistrationBean = new FilterRegistrationBean();
        traceFilterFilterRegistrationBean.setFilter(new TraceFilter());
        traceFilterFilterRegistrationBean.setOrder(2);
        traceFilterFilterRegistrationBean.setName("TraceFilter");
        traceFilterFilterRegistrationBean.setEnabled(true);
        return traceFilterFilterRegistrationBean;
    }

    public static void main(String[] args) {
        SpringApplication.run(TravelCasestudyApplication.class, args);
    }

}
