package com.javajosh.pim;

import ch.qos.logback.classic.Logger;
import io.dropwizard.logging.LoggingUtil;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Optional;
import java.util.Random;

/**
 * Request handler or servlet, called a Resource in Dropwizard; should be threadsafe.
 */
@Path("/item")
@RegisterBeanMapper(ItemResource.class)
@Produces(MediaType.APPLICATION_JSON)
public class ItemResource {
  final Logger log = LoggingUtil.getLoggerContext().getLogger(ItemResource.class);
  private final Jdbi jdbi;

  public ItemResource(Jdbi jdbi) {
    this.jdbi = jdbi;
  }

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
    int randomId = new Random().nextInt();
    log.debug("hello {}", randomId);
    return new Item(randomId, name.orElse(""));
  }

  @GET
  @Path("/{id}")
  public Item getItem(@PathParam("id") int id) {
    log.debug("Entered /item/{} -> ItemResource.getItem({})", id, id);
    return jdbi.withExtension(ItemDAO.class, dao -> dao.findById(id));
  }
}
