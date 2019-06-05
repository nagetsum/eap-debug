package demo;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.List;

public class PlanetsMoons {
    private String currentPlanet;

    public List<SelectItem> planetsList = new ArrayList<SelectItem>();
    public List<String> moonsList = new ArrayList<String>();
    private static final String[] EARTH = {"The Moon"};
    private static final String[] MARS = {"Deimos", "Phobos"};
    private static final String[] JUPITER = {"Europa", "Gamymede", "Callisto"};

    public PlanetsMoons() {
        SelectItem item = new SelectItem("earth", "Earth");
        planetsList.add(item);
        item = new SelectItem("mars", "Mars");
        planetsList.add(item);
        item = new SelectItem("jupiter", "Jupiter");
        planetsList.add(item);
    }

    public void planetChanged(ValueChangeEvent event) {
        moonsList.clear();
        String[] currentItems;
        if (((String) event.getNewValue()).equals("earth")) {
            currentItems = EARTH;
        } else if (((String) event.getNewValue()).equals("mars")) {
            currentItems = MARS;
        } else {
            currentItems = JUPITER;
        }

        for (int i = 0; i < currentItems.length; i++) {
            moonsList.add(currentItems[i]);
        }
    }

    public String getCurrentPlanet() {
        return currentPlanet;
    }

    public void setCurrentPlanet(String currentPlanet) {
        this.currentPlanet = currentPlanet;
    }

    public List<SelectItem> getPlanetsList() {
        return planetsList;
    }

    public void setPlanetsList(List<SelectItem> planetsList) {
        this.planetsList = planetsList;
    }

    public List<String> getMoonsList() {
        return moonsList;
    }

    public void setMoonsList(List<String> moonsList) {
        this.moonsList = moonsList;
    }
}
