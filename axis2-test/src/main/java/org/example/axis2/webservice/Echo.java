package org.example.axis2.webservice;

import javax.jws.WebService;

@WebService
public interface Echo {
    String echo(String s);
}
