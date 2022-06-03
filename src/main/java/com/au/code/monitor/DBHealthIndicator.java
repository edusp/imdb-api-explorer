package com.au.code.monitor;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class DBHealthIndicator implements HealthIndicator {

  @Override
  public Health health() {
    if (isDBHealth()) {
      return Health.up().withDetail("External DB or svc", "Health").build();
    }
    return Health.down().withDetail("External DB or svc", "Is Not-Health").build();
  }


  //Mimic a call to an external service or DB
  private boolean isDBHealth() {
    return new Random().nextBoolean();
  }

}
