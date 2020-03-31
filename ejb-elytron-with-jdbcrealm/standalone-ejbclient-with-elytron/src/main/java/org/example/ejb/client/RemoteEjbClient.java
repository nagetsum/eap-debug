package org.example.ejb.client;

import org.example.ejb.server.Echo;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class RemoteEjbClient {
    public static void main(String ... args) {
        Properties props = new Properties();
        props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        props.put(Context.PROVIDER_URL, "remote+http://localhost:8080");

        Context context = null;
        try {
            context = new InitialContext(props);
            Echo echo = (Echo) context.lookup("ejb:/ejb-security/EchoBean!" + Echo.class.getName());
            System.out.println(echo.echo("Hello, world!"));
        } catch (NamingException e ) {
            e.printStackTrace();
        } finally {
            closeQuietly(context);
        }
    }

    private static final void closeQuietly(Context context) {
        if (context != null) {
            try {
                context.close();
            } catch (NamingException e) {
            }
        }
    }
}
