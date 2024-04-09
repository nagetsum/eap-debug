package org.example.spring.session.demo.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@WebListener
public class CleanupSpringSessionSchemaListener implements ServletContextListener {
    private static final Logger LOG = LoggerFactory.getLogger(CleanupSpringSessionSchemaListener.class);

    @Resource(lookup = "java:jboss/datasources/PostgresDS")
    DataSource dataSource;

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("org/springframework/session/jdbc/schema-drop-postgresql.sql"));
        resourceDatabasePopulator.execute(dataSource);
        LOG.info("table spring_session and spring_session_attributes are dropped");
    }
}