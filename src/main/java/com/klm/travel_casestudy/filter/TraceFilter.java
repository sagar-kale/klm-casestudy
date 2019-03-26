package com.klm.travel_casestudy.filter;

import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class TraceFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        this.insertIntoMDC(httpServletRequest);
        try {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } finally {
            this.clearMDC();
        }
    }

    void insertIntoMDC(HttpServletRequest httpServletRequest) {
        MDC.put("req.id", UUID.randomUUID().toString());
        MDC.put("req.remoteHost", httpServletRequest.getRemoteHost());
        MDC.put("req.requestURI", httpServletRequest.getRequestURI());
        StringBuffer requestURL = httpServletRequest.getRequestURL();
        if (requestURL != null) {
            MDC.put("req.requestURL", requestURL.toString());
        }

        MDC.put("req.method", httpServletRequest.getMethod());
        MDC.put("req.queryString", httpServletRequest.getQueryString());
        MDC.put("req.userAgent", httpServletRequest.getHeader("User-Agent"));
        MDC.put("req.xForwardedFor", httpServletRequest.getHeader("X-Forwarded-For"));


    }

    void clearMDC() {
        MDC.remove("req.remoteHost");
        MDC.remove("req.requestURI");
        MDC.remove("req.queryString");
        MDC.remove("req.requestURL");
        MDC.remove("req.method");
        MDC.remove("req.userAgent");
        MDC.remove("req.xForwardedFor");
    }
}
