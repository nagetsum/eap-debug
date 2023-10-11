package org.jboss.example;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;

public class ConnectionValidator extends HttpServlet {

    private static final String SQL = "SELECT 1";              // for PostgreSQL
//    private static final String SQL = "SELECT id, title, author FROM book";  // for Oracle
//    private static final String SQL = "SELECT pg_sleep(10)"; // test for slow query with PostgreSQL
//    private static final String SQL = "SELECT * FROM DUAL";  // for Oracle

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
            res.getWriter().write("connection is valid");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // Do not use try-with-resource for ease of checking for connection closure leaks
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
        System.out.println("### REQUEST DONE");
    }

    private DataSource lookup() {
        try {
//            return (DataSource) InitialContext.doLookup("java:jboss/datasources/OracleDS");
//            return (DataSource) InitialContext.doLookup("java:jboss/datasources/PostgresDS");
//            return (DataSource) InitialContext.doLookup("java:comp/env/jdbc/OracleDS"); // for Tomcat
            return (DataSource) InitialContext.doLookup("java:comp/env/jdbc/PostgresDS"); // for Tomcat
//            return (DataSource) InitialContext.doLookup("jdbc/OracleDS"); // for Tomcat global JNDI context
//            return (DataSource) InitialContext.doLookup("java:comp/env/jdbc/SampleDS");  // for Tomcat
//            return (DataSource) InitialContext.doLookup("java:/PostgresDS");    // for EAP5
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
}
