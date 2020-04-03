package sample.servlet;

import javax.annotation.Resource;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/jdbc")
public class JdbcServlet extends HttpServlet {

    @Resource(lookup = "java:jboss/datasources/PostgresDS")
    private DataSource ds;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws IOException {

        res.setContentType("text/plain; charset=utf-8");
        PrintWriter out = res.getWriter();

        try (Connection conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement("select 1")) {
            ps.executeQuery();
            out.println("Datasource PostgresDS is working fine.");
        } catch (SQLException e) {
            e.printStackTrace(out);
        }
    }
}
