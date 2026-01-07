package com.hackathon.continuum.infra.filtros;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
@Order(1)
public class LogInterceptorFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String traceId = UUID.randomUUID().toString().substring(0, 8);
        MDC.put("traceId", traceId);

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        try {
            log.info("Iniciando requisição: [{} {}]", request.getMethod(), request.getRequestURI());
            filterChain.doFilter(request, response);
        } finally {
            stopWatch.stop();
            log.info("Finalizando requisição: [{} {}] | Status: {} | Tempo: {}ms",
                    request.getMethod(),
                    request.getRequestURI(),
                    response.getStatus(),
                    stopWatch.getTotalTimeMillis());
            MDC.remove("traceId");
        }
    }
}