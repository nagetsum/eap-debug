package org.example.jafile;

import javax.servlet.annotation.WebServlet;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.io.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;

@Path("/files_jio")
public class FileJioResource {
    @POST
    public Response createNewJaFile() {
        try (FileOutputStream out = new FileOutputStream("/opt/eap/日本語ファイル.txt")) {
            out.write("あいうえお".getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
        return Response.created(URI.create("file:///opt/eap/日本語ファイル.txt")).build();
    }

    @GET
    public Response readJaFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("/opt/eap/日本語ファイル.txt"))) {
            String str;
            while ((str = reader.readLine()) != null) {
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
        return Response.ok().build();
    }
}
