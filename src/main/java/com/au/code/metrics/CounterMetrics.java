package com.au.code.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.prometheus.client.Summary;
import org.springframework.stereotype.Component;


@Component
public class CounterMetrics {

  private final Counter counter;

  public CounterMetrics(MeterRegistry registry) {
    this.counter = Counter.builder("endpont_hits_counter")
            .description("Number of Hits")
            .register(registry);
  }


  public void increment() {
    getCounter().increment();
  }

  public static final Summary requestLatency = Summary.build()
          .name("requests_latency_seconds")
          .help("request latency in seconds")
          .register();


  private Counter getCounter() {
    return counter;
  }
}
