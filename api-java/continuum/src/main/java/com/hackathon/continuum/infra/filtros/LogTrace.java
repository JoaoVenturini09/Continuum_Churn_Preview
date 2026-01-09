package com.hackathon.continuum.infra.filtros;

import org.springframework.stereotype.Component;

@Component
public class LogTrace {
    public static String getTraceId() {
        return org.slf4j.MDC.get("traceId");
    }
}
