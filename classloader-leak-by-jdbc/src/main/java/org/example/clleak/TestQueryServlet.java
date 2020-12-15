package org.example.clleak;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/test-query")
public class TestQueryServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/postgres";

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws IOException {
        res.setContentType("text/plain; charset=utf-8");
        res.getWriter().println("Is DB connection valid? :" + isConnectionValid());
    }

    private boolean isConnectionValid() {
        try (Connection conn = DriverManager.getConnection(DB_URL, "postgres", "postgres");
             PreparedStatement ps = conn.prepareStatement("SELECT 1")) {
            ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
