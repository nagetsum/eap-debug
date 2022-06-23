package org.example.jasperreport;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JsonDataSource;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/download")
public class ReportDownloadServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws IOException {

        resp.setHeader("Content-Type", "application/octet-stream");
        resp.setHeader("Content-Disposition", "attachment;filename=MyBooks.pdf");

        Map<String, Object> parameters = new HashMap<>();

        try (InputStream template = this.getClass().getResourceAsStream("/example/books.jrxml");
             InputStream jsonFile = this.getClass().getResourceAsStream("/example/books.json")) {

            JsonDataSource dataSource = new JsonDataSource(jsonFile, "books");
            JasperReport report = JasperCompileManager.compileReport(template);
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, dataSource);
            JasperExportManager.exportReportToPdfStream(jasperPrint, resp.getOutputStream());
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }
}
