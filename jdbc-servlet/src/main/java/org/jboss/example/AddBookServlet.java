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
import java.util.concurrent.atomic.AtomicInteger;

public class AddBookServlet extends HttpServlet {
    private AtomicInteger count = new AtomicInteger();
    private static final String SQL = "INSERT INTO book values (?, ?, ?)";

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
            ps.setInt(1, count.incrementAndGet());
            ps.setString(2, "test-author-" + count.get());
            ps.setString(3, "test-title-" + count.get());

            ps.executeUpdate();
            res.getWriter().write("add book number=" + count.get());
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
    }

    private DataSource lookup() {
        try {
            return (DataSource) InitialContext.doLookup("jdbc/OracleDS");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
}
