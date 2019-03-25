package sample.bean;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@ViewScoped
@Named
public class RegisterFormBean implements Serializable {

    @Inject
    private Prefectures prefs;

    private String selectedPrefCode;
    private String selectedCity;
    private Prefecture selectedPrefecture;

    @PostConstruct
    void init() {
        // Default is Tokyo
        this.selectedPrefCode = "13";
        this.selectedPrefecture = prefs.get(selectedPrefCode);
        this.selectedCity = selectedPrefecture.getCities().get(0);
        System.out.println("new RegisterFormBean: " + this.toString());
    }

    public void onChangePref() {
        this.selectedPrefecture = prefs.get(selectedPrefCode);
        this.selectedCity = selectedPrefecture.getCities().get(0);
        System.out.println("change state by ajax listener: " + this.toString());
    }

    public String nextPage() {
        this.selectedPrefecture = prefs.get(selectedPrefCode);

        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        flash.put("prefName", selectedPrefecture.getName());
        flash.put("cityName", selectedCity);
        return "result.xhtml?faces-redirect=true";
    }


    public Prefectures getPrefs() {
        return prefs;
    }

    public String getSelectedPrefCode() {
        return selectedPrefCode;
    }

    public void setSelectedPrefCode(String selectedPrefCode) {
        this.selectedPrefCode = selectedPrefCode;
    }

    public String getSelectedCity() {
        return selectedCity;
    }

    public void setSelectedCity(String selectedCity) {
        this.selectedCity = selectedCity;
    }

    public Prefecture getSelectedPrefecture() {
        return selectedPrefecture;
    }

    @Override
    public String toString() {
        return "RegisterFormBean{" +
                "selectedPrefCode='" + selectedPrefCode + '\'' +
                ", selectedCity='" + selectedCity + '\'' +
                ", selectedPrefecture=" + selectedPrefecture +
                '}';
    }
}
