package org.example.redhat.cee.eclipselink.rs;

import org.example.redhat.cee.eclipselink.entity.Employee;
import org.example.redhat.cee.eclipselink.service.EmployeeService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/employees")
@ApplicationScoped
public class EmployeeResource {

    @Inject
    EmployeeService employeeService;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Employee get(@PathParam("id") long id) {
        Employee result = employeeService.getById(id);
        System.out.println("result = " + result);
        return result;
    }

    @POST
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Employee post(@PathParam("id") long id) {
        Employee result = employeeService.addUser(id, "Test User-" + id);
        System.out.println("result = " + result);
        return result;
    }
}
