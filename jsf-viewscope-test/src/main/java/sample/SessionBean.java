package sample;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@SessionScoped
@Named
public class SessionBean implements Serializable {

    private int count;

    public int incrementAndGet() {
        count++;
        return count;
    }

    public int now() {
        return count;
    }
}
