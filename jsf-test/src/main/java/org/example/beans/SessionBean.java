package org.example.beans;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
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
        return count;
    }
}
