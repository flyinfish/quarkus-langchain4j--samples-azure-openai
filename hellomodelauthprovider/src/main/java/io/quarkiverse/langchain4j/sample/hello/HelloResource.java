package io.quarkiverse.langchain4j.sample.hello;

import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Multi;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/hello")
public class HelloResource {
  @Inject HelloAI helloAI;

  @GET
  public String hello(@QueryParam("message") Optional<String> message) {
    return helloAI.chat(message.orElse("Hi"));
  }

  @GET
  @Path("/stream")
  @Produces(MediaType.SERVER_SENT_EVENTS)
  public Multi<String> helloStream(@QueryParam("message") Optional<String> message) {
    return helloAI.chatStream(message.orElse("Hi"));
  }
}
