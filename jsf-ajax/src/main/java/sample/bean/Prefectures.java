package sample.bean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.*;

@ApplicationScoped
@Named
public class Prefectures {
    private final Map<String, Prefecture> list;

    public Prefectures() {
        // TODO: get pref data from Database or external configuration file
        Map<String, Prefecture> prefs = new LinkedHashMap<>();
        prefs.put("13", new Prefecture("13", "Tokyo", Arrays.asList("Chuo-ku", "Minato-ku", "Chiyoda-ku")));
        prefs.put("12", new Prefecture("12", "Chiba", Arrays.asList("Chiba-shi", "Funabashi-shi", "Kashiwa-shi")));
        prefs.put("11", new Prefecture("11", "Saitama", Arrays.asList("Saitama-shi", "Kawaguchi-shi", "Kawagoe-shi")));
        prefs.put("14", new Prefecture("14", "Kanagawa", Arrays.asList("Yokohama-shi", "Kawasaki-shi", "Odawara-shi")));
        this.list = Collections.unmodifiableMap(prefs);
    }

    public Collection<Prefecture> getList() {
        return list.values();
    }
    public Prefecture get(String prefCode) {
        return list.get(prefCode);
    }
}
