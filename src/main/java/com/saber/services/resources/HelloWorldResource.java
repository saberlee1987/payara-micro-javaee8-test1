package com.saber.services.resources;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path(value = "hello")
public class HelloWorldResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject helloWorld() {
        String hostname = Optional.ofNullable(System.getenv("HOSTNAME"))
                .orElse("localhost");
        return Json.createObjectBuilder()
                .add("message", "Cloud Native Application Development with Java EE")
                .add("hostname", hostname)
                .build();
    }
}
