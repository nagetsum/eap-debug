package org.example.ejb.server;

import javax.ejb.Remote;

@Remote
public interface Echo {
    String echo(String s);
}
