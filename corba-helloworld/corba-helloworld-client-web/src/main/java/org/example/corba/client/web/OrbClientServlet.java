package org.example.corba.client.web;

import HelloApp.Hello;
import HelloApp.HelloHelper;
import org.omg.CORBA.ORB;
import org.omg.CORBA.UserException;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/client")
public class OrbClientServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws IOException {
        res.setContentType("text/plain; charset=utf-8");
        res.getWriter().println("call ORB result: " + callOrb());
    }

    private String callOrb() {
        try{
            ORB orb = (ORB) new InitialContext().lookup("java:comp/ORB");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(orb.string_to_object("corbaloc:iiop:127.0.0.1:1050/NameService"));
            Hello helloImpl = HelloHelper.narrow(ncRef.resolve_str("Hello"));
            return helloImpl.sayHello();
        } catch (NamingException | UserException e) {
            throw new RuntimeException(e);
        }
    }
}
