package sample.fileupload.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebServlet("/upload")
@MultipartConfig
public class FileuploadServlet extends HttpServlet {

    private static final String FILE_STORE_DIR = "/home/nagetsum/store/";

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws IOException, ServletException {

        res.setContentType("text/plain; charset=utf-8");
        PrintWriter out = res.getWriter();

        for (Part part : req.getParts()) {
            String fileName = part.getSubmittedFileName();
            if (!fileName.isBlank()) {
                try (InputStream in = part.getInputStream()) {
                    Files.copy(in , Paths.get(FILE_STORE_DIR + fileName));
                    out.println("file upload succeed, file name : " + part.getSubmittedFileName());
                }
            }
        }
    }
}