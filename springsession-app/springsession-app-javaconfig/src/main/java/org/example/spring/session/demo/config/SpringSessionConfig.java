package org.example.spring.session.demo.config;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.jndi.JndiTemplate;
import org.springframework.session.jdbc.config.annotation.SpringSessionDataSource;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.transaction.PlatformTransactionManager;

@EnableJdbcHttpSession
public class SpringSessionConfig {

    @Value("#{servletContext.contextPath}")
    private String servletContextPath;

    @Bean
    @SpringSessionDataSource
    public DataSource dataSource() throws NamingException {
        return new JndiTemplate().lookup("java:jboss/datasources/PostgresDS", DataSource.class);
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("org/springframework/session/jdbc/schema-postgresql.sql"));

        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        return dataSourceInitializer;
    }

    // workaround for #22319 and #1040
    // Spring Session with java config does not work due to #22319.
    // https://github.com/spring-projects/spring-framework/issues/22319
    // https://github.com/spring-projects/spring-session/issues/1040
    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
        cookieSerializer.setCookieName("JSESSIONID");          // default session cookie name of JBoss EAP is JSESSIONID
//        cookieSerializer.setCookieName("SPRINGSESSIONID");
        cookieSerializer.setCookiePath(servletContextPath);    // set context-root to cookie path
        return cookieSerializer;
    }
}
