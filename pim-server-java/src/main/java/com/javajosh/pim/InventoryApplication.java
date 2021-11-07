package com.javajosh.pim;

import io.dropwizard.Application;
import io.dropwizard.client.HttpClientBuilder;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.apache.http.client.HttpClient;


public class InventoryApplication extends Application<InventoryConfiguration> {

  @Override /* We can put things into the bootstrap before our own run code is called. */
  public void initialize(Bootstrap<InventoryConfiguration> bootstrap) {
    bootstrap.addCommand(new HttpClientCommand());
  }

  public static void main(String[] args) throws Exception {
    new InventoryApplication().run(args);
  }

  @Override /* This is a template method called indirectly by the run(String[]) method */
  public void run(InventoryConfiguration config, Environment environment) {
    environment.healthChecks().register("db-health", new DBHealthCheck());

    final HttpClient httpClient = new HttpClientBuilder(environment)
      .using(config.getHttpClientConfiguration())
      .build(getName());
    environment.jersey().register(httpClient);

    // Finally, register resources
    environment.jersey().register(new ItemResource());
  }
}
