package com.epam.netflix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.trace.DefaultTracer;
import org.springframework.stereotype.Component;

@Component
public class DemoCall {

    @Autowired
    DefaultTracer tracer;

    public void hiHello() {
        Span span = tracer.createSpan("newService");
        span.tag("testtag","mytag");
        span.logEvent(org.springframework.cloud.sleuth.Span.CLIENT_SEND);
        System.out.println("hdlhfsdf");
        span.tag("peer.service", "newService");
        span.tag("peer.ipv4", "1.2.3.4");
        span.tag("peer.port", "1234");
        span.logEvent(org.springframework.cloud.sleuth.Span.CLIENT_RECV);
        tracer.close(span);
    }
}
