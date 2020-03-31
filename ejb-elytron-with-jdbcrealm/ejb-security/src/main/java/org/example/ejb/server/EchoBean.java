package org.example.ejb.server;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import java.security.Principal;

@Stateless
@PermitAll
public class EchoBean implements Echo {
    @Resource
    private SessionContext context;

    @Override
    public String echo(String s) {
        Principal principal = context.getCallerPrincipal();
        return "echo: " + s + ", principal = " + principal.getName();
    }
}