package org.example.jsf;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;

@SessionScoped
@Named
public class SessionBean implements Serializable {

    private int count;

    public int incrementAndGet() {
        this.count++;
        return count;
    }

    public void reset() {
        this.count = 0;
    }

    public int now() {
        System.out.println("### call now");
        return count;
    }
}
