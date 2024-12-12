package io.quarkiverse.langchain4j.sample.hello;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/hello")
public class HelloResource {
    @Inject
    HelloAI helloAI;

    @GET
    public String hello() {
        return helloAI.chat("Hello");
    }
    
}