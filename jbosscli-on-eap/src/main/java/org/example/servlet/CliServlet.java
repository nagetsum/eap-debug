package org.example.servlet;

import org.jboss.as.cli.scriptsupport.CLI;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/state")
public class CliServlet extends HttpServlet {

    private static final String CLI_COMMAND_SERVER_STATE = ":read-attribute(name=server-state)";

    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws IOException {

        CLI cli = CLI.newInstance();
        cli.connect();
        String serverState = cli.cmd(CLI_COMMAND_SERVER_STATE).getResponse().get("result").asString();
        cli.disconnect();

        res.setContentType("text/plain; charset=utf-8");
        res.getWriter().println("serverState = " + serverState);
    }
}
