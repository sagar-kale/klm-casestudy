package com.klm.travel_casestudy.config;

import com.klm.travel_casestudy.common.ExceptionHandler;
import com.klm.travel_casestudy.domain.Endpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenProvider;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

import java.util.Arrays;

@Configuration
@Slf4j
public class OauthClientConfig {

    @Autowired
    private Endpoint endpoint;


    @Bean
    public OAuth2RestOperations restTemplate() {

        OAuth2RestTemplate template = new OAuth2RestTemplate(fullAccessresourceDetailsClientOnly(), new DefaultOAuth2ClientContext(
                new DefaultAccessTokenRequest()));
        return prepareTemplate(template, true);
    }

    public OAuth2RestTemplate prepareTemplate(OAuth2RestTemplate template, boolean isClient) {
        template.setErrorHandler(new ExceptionHandler());
        if (isClient) {
            template.setAccessTokenProvider(clientAccessTokenProvider());
        }
        return template;
    }

    /**
     * {@link AccessTokenProviderChain} throws
     * InsufficientAuthenticationException in
     * obtainAccessToken(OAuth2ProtectedResourceDetails resource,
     * AccessTokenRequest request) if user is not authorized, but since we are
     * setting our own accessTokenProvider() on OAuth2RestTemplate this
     * condition is not being checked, thus exception is not being thrown and
     * requirement for user to be logged in is skipped
     */

    @Bean
    public AccessTokenProvider clientAccessTokenProvider() {
        ClientCredentialsAccessTokenProvider accessTokenProvider = new ClientCredentialsAccessTokenProvider();
        return accessTokenProvider;
    }

    @Bean
    @Qualifier("myClientOnlyFullAcessDetails")
    public OAuth2ProtectedResourceDetails fullAccessresourceDetailsClientOnly() {
        ClientCredentialsResourceDetails resource = new ClientCredentialsResourceDetails();
        log.info("tokennnnnn {}", endpoint.getTokenUrl());
        resource.setAccessTokenUri(endpoint.getTokenUrl());
        resource.setClientId(endpoint.getClientId());
        resource.setClientSecret(endpoint.getSecret());
        resource.setGrantType(endpoint.getGrantType());
        resource.setScope(Arrays.asList("read", "write"));
        return resource;
    }
}