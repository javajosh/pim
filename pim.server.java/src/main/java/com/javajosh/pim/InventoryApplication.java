package com.javajosh.pim;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.client.HttpClientBuilder;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.apache.http.client.HttpClient;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;


public class InventoryApplication extends Application<InventoryConfiguration> {



  public static void main(String[] args) throws Exception {
    new InventoryApplication().run(args); //invoke the DW run method
  }

  @Override /* Add commands to the bootstrap before our own run() code is called. */
  public void initialize(Bootstrap<InventoryConfiguration> bootstrap) {
    bootstrap.addCommand(new HttpClientCommand());

    // Enable variable substitution with environment variables
    bootstrap.setConfigurationSourceProvider(
      new SubstitutingSourceProvider(
        bootstrap.getConfigurationSourceProvider(),
        new EnvironmentVariableSubstitutor(false)
      )
    );

    // Relative paths don't work because the AssetsBundle only serves from classpath
    bootstrap.addBundle(new AssetsBundle("../pim-client-angular/dist", "/"));
    // Creating a symlink doesn't work practically, but it also wouldn't work
    // theoretically because the assetsBundle isn't doing normal File IO
    // PS C:\> cd pim\pim-server-java\src\main\resources
    // PS C:\> New-Item -Path bundle -ItemType  SymbolicLink -Value ..\..\..\..\pim-client-angular\dist

  }

  @Override /* This is a template method called indirectly by the run(String[]) method */
  public void run(InventoryConfiguration config, Environment environment) {
    // Instantiate and register remaining server components
    environment.healthChecks().register("db-health", new DBHealthCheck());

    final HttpClient httpClient = new HttpClientBuilder(environment)
      .using(config.getHttpClientConfiguration())
      .build(getName());
    environment.jersey().register(httpClient);

    final JdbiFactory factory = new JdbiFactory();
    final Jdbi jdbi = factory.build(environment, config.getDataSourceFactory(), "postgresql");
    jdbi.installPlugin(new SqlObjectPlugin());

    // Finally, register resources
    environment.jersey().register(new ItemResource(jdbi));
  }
}
