package sample.jdbc.rs;

import sample.jdbc.bean.ConnectionValidator;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/conn")
@ApplicationScoped
public class ConnectionResouce {

    @Inject
    private ConnectionValidator connectionValidator;

    @GET
    @Path("/ds/status")
    @Produces(APPLICATION_JSON)
    public Status status() {
        return new Status("java:jboss/datasources/PostgresDS", connectionValidator.isValid());
    }
}
