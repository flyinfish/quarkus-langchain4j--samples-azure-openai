package io.quarkiverse.langchain4j.sample.hello;

import io.smallrye.mutiny.Multi;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class HelloResource {
    @Inject
    HelloAI helloAI;

    @GET
    public String hello() {
        return helloAI.chat("Hello");
    }
    
    @GET
    @Path("/stream")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public Multi<String> helloStream() {
        return helloAI.chatStream("Hello");
    }
    
}