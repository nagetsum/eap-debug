package org.example.clleak;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.PrintWriter;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;

@WebListener
public class DriverDeregisterListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        // To enable logging in java.sql.DriverManager class.
        DriverManager.setLogWriter(new PrintWriter(System.out));
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        Collections.list(DriverManager.getDrivers())
                .forEach(DriverDeregisterListener::deregisterDriver);
    }

    private static void deregisterDriver(Driver driver) {
        try {
            DriverManager.deregisterDriver(driver);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
