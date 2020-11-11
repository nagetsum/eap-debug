package org.example.jsr77;

import javax.ejb.CreateException;
import javax.management.*;
import javax.management.j2ee.Management;
import javax.management.j2ee.ManagementHome;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet("/query")
public class QueryManagementInfoServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws IOException, ServletException {
        try {
            ManagementHome home = (ManagementHome) InitialContext.doLookup("ejb/mgmt/MEJB");
            Management mejb = home.create();
            ObjectName query = new ObjectName("*:j2eeType=JVM,*");
            Set<ObjectName> s = mejb.queryNames(query, null);
            for (ObjectName on : s) {
                System.out.println("ObjectName: " + on + ", javaVendor= " + mejb.getAttribute(on,"javaVendor")
                        + ", javaVersion=" + mejb.getAttribute(on, "javaVersion")
                        + ", statisticsProvider=" + mejb.getAttribute(on, "statisticsProvider"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
