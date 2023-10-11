package org.jboss.example;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SlowQuery extends HttpServlet {
    private static final String SQL = "SELECT pg_sleep(10)";

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        res.setContentType("text/plain; charset=utf-8");

        DataSource ds = lookup();
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement(SQL);
            ps.executeQuery();
            res.getWriter().write("10 seconds query done");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("### SLOW QUERY REQUEST DONE");
    }

    private DataSource lookup() {
        try {
            return (DataSource) InitialContext.doLookup("java:jboss/datasources/PostgresDS");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
}
