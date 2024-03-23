package org.fk.backend.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.fk.core.manager.GreetingManager;

@Path("/hello")
public class GreetingController {

    @Inject
    GreetingManager greetingService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return greetingService.test();
    }
}
