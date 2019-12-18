package sample;

import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Model
public class BackingBean {

    @Inject
    private ViewScopedBean viewScoped;

    @Inject
    private SessionBean sessionScoped;

    public void increment() {
        viewScoped.incrementAndGet();
        sessionScoped.incrementAndGet();
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "logout";
    }
}