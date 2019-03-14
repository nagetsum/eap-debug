package sample.jdbc.bean;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@ApplicationScoped
public class ConnectionValidator {

    @Resource(lookup = "java:jboss/datasources/PostgresDS")
    private DataSource ds;

    public boolean isValid() {
        try (Connection conn = ds.getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT 1")) {
            ps.executeQuery();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
