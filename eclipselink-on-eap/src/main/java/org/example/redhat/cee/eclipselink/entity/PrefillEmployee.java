package org.example.redhat.cee.eclipselink.entity;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Singleton
@Startup
public class PrefillEmployee {

    @Resource(lookup = "java:jboss/datasources/PostgresDS")
    private DataSource ds;

    @PostConstruct
    public void prefill() {
        try (Connection conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO employee VALUES (1, 'Norito')")) {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
