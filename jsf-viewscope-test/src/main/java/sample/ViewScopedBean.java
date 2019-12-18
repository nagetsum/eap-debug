package sample;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
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
