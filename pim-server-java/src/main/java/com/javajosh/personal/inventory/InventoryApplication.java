package com.javajosh.personal.inventory;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class InventoryApplication extends Application<InventoryConfiguration> {

    // This is the main entry point, but you are immediately delegating to components
    // that require
    public static void main(String[] args) throws Exception{
        new InventoryApplication().run(args);
    }

    @Override /* All of this to put two default Commands in the bootstrap. Sigh. */
    public void initialize(Bootstrap<InventoryConfiguration> bootstrap) {
        // Put stuff into the bootstrap.
    }

    @Override /* This is a template method called indirectly by the run(String[]) method */
    public void run(InventoryConfiguration inventoryConfiguration, Environment environment) {
        environment.jersey().register(new ItemResource());
        environment.healthChecks().register("db-health", new DBHealthCheck());
    }
}
