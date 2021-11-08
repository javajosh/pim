package com.javajosh.pim;

import ch.qos.logback.classic.Logger;
import io.dropwizard.logging.LoggingUtil;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;
import java.util.Random;

/**
 * Request handler or servlet, called a Resource in Dropwizard; should be threadsafe.
 */
@Path("/item")
@Produces(MediaType.APPLICATION_JSON)
public class ItemResource {
  final Logger log = LoggingUtil.getLoggerContext().getLogger(ItemResource.class);

  public ItemResource() {}

  /*
   * /item/random?name=foobar => Item(id=random(), name=foobar).json()
   * Generate a synthetic Item and return it.
   * A named function with named string args that returns an instance of the static Item type.
   * JAXWS is used to annotate the class, method, and params to map to the URL.
   */
  @GET
  @Path("/random")
  public Item getRandomItem(@QueryParam("name") Optional<String> name) {
    // do anything here!
    long randomId = new Random().nextLong();
    log.debug("hello {}", randomId);
    return new Item(randomId, name.orElse(""));
  }

  @GET
  public Item getItem(@QueryParam("id") int id) {
    // do anything here!
    return new Item(id, "josh");
  }
}
