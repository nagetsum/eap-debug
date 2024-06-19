package org.example.jsf;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;

@ViewScoped
@Named
public class ViewScopedBean implements Serializable {


    private int count;

    public int incrementAndGet() {
        count++;
        return count;
    }

    public int now() {
        return count;
    }
}
