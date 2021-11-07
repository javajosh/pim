package com.javajosh.pim;

import com.codahale.metrics.health.HealthCheck;

public class DBHealthCheck extends HealthCheck {
  public DBHealthCheck() {
  }

  @Override
  protected Result check() {
    // right now this does nothing
    return Result.healthy();
  }
}
