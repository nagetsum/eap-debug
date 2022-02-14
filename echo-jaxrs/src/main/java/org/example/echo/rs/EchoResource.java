package org.example.echo.rs;

import javax.enterprise.context.ApplicationScoped;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/echo")
@ApplicationScoped
public class EchoResource {

    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response echo() {
        return Response.ok().build();
    }
}
