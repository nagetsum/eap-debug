package org.example.echo.rs;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/employees")
public class EmployeeResource {
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEmployee(Employee newHire, @Context UriInfo uri) {
        System.out.println(newHire);
        return Response
                .created(uri.getAbsolutePathBuilder().path(String.valueOf(newHire.getId())).build())
                .build();
    }
}
