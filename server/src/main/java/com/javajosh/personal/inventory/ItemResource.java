package com.javajosh.personal.inventory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;
import java.util.Random;

/** Request handler or servlet,  called a Resource in Dropwizard, should be threadsafe. */
@Path("/item")
@Produces(MediaType.APPLICATION_JSON)
public class ItemResource {
    public ItemResource(){}

    /* Incredibly clunky way to specify an optional with default */
    @GET
    public Item getRandomItem(@QueryParam("name") Optional<String> name){
        return new Item(new Random().nextLong(), name.orElse(""));
    }
}
