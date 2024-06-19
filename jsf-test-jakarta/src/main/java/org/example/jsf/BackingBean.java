package org.example.jsf;

import jakarta.enterprise.inject.Model;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;

@Model
public class BackingBean {

    @Inject
    private ViewScopedBean viewScoped;

    @Inject
    private SessionBean sessionScoped;

    public void increment() {
        System.out.println("### BackingBean#increment() - start");
        viewScoped.incrementAndGet();
        sessionScoped.incrementAndGet();
        System.out.println("### BackingBean#increment() - finish");
//        return "index.xhtml?faces-redirect=true";
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "logout";
    }
}
