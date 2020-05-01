package org.example.remotejmx.servlet;

import javax.management.JMException;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/server_state")
public class JmxClientServlet extends HttpServlet {

    private static final String JMX_URL = "service:jmx:remote+http://127.0.0.1:9990";

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws IOException {

        res.setContentType("text/plain; charset=utf-8");
        PrintWriter out = res.getWriter();

        // If target JBoss run remotely, the authentication credential is required.
//        Map<String, String[]> map = new HashMap();
//        String[] credentials = new String[] { "<User in ManagementRealm adding by $JBOSS_HOME/bin/add-user.sh>", "<your-password>" };
//        map.put(JMXConnector.CREDENTIALS, credentials);

        JMXServiceURL serviceURL = new JMXServiceURL(JMX_URL);
        try (JMXConnector jmxConnector = JMXConnectorFactory.connect(serviceURL, null)) {
            MBeanServerConnection connection = jmxConnector.getMBeanServerConnection();
            ObjectName objectName = ObjectName.getInstance("jboss.as:management-root=server");
            String attributeName = "serverState";
            Object result = connection.getAttribute(objectName, attributeName);
            out.println("serverState = " + result);
        } catch (JMException e) {
           e.printStackTrace(out);
        }
    }
}
