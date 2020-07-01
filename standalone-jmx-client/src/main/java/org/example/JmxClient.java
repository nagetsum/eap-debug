package org.example;

import javax.management.JMException;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.net.MalformedURLException;

public class JmxClient {

    private static final String JMX_URL = "service:jmx:remote+http://127.0.0.1:9990";

    public static void main(String ... args) throws MalformedURLException {
        // If target JBoss instance is not running on localhost, the authentication credential is required.
//        Map<String, String[]> map = new HashMap();
//        String[] credentials = new String[] { "<User in ManagementRealm adding by $JBOSS_HOME/bin/add-user.sh>", "<your-password>" };
//        map.put(JMXConnector.CREDENTIALS, credentials);

        JMXServiceURL serviceURL = new JMXServiceURL(JMX_URL);
        try (JMXConnector jmxConnector = JMXConnectorFactory.connect(serviceURL, null)) {
            MBeanServerConnection connection = jmxConnector.getMBeanServerConnection();
            ObjectName objectName = ObjectName.getInstance("jboss.as:management-root=server");
            String attributeName = "serverState";
            Object result = connection.getAttribute(objectName, attributeName);
            System.out.println("serverState = " + result);
        } catch (JMException | IOException e) {
            e.printStackTrace();
        }
    }
}
