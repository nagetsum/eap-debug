package org.example.cxf.jaxrs;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/user")
public class UserResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User get() {
        return User.of(1, "shadow-man");
    }
}
