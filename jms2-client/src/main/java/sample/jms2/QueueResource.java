package sample.jms2;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/queue")
public class QueueResource {

    @Inject
    QueueClient queueClient;

    @POST
    public Response push(@QueryParam("message") String message) {
        queueClient.send(message);
        return Response.accepted().build();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String consumeOne() {
        return queueClient.consume().orElseThrow(() -> new NotFoundException("HelloQueue is empty"));
    }
}
