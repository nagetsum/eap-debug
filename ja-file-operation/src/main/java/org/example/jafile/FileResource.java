package org.example.jafile;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

@Path("/files")
public class FileResource {
    @POST
    public Response createNewJaFile() {
        try {
            java.nio.file.Path path = Files.createFile(Paths.get("/opt/eap/日本語ファイル.txt"));
            Files.write(path, Arrays.asList("あいうえお"), StandardOpenOption.WRITE);
        } catch (IOException e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
        return Response.created(URI.create("file:///opt/eap/日本語ファイル.txt")).build();
    }

    @GET
    public Response readJaFile() {
        try {
            Files.readAllLines(Paths.get("/opt/eap/日本語ファイル.txt"))
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
        return Response.ok().build();
    }
}
