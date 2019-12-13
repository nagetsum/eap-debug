package sample.jsf.multiwindow;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class Counter implements Serializable {
    private static final long serialVersionUID = 1L;

    private int now;

    public void increment() {
        now++;
    }

    public int now() {
        return now;
    }
}
