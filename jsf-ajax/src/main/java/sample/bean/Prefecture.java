package sample.bean;

import java.util.Collections;
import java.util.List;

public class Prefecture {
    private final String code;
    private final String name;
    private final List<String> cities;

    public Prefecture(String code, String name, List<String> cities) {
        this.code = code;
        this.name = name;
        this.cities = cities;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public List<String> getCities() {
        return Collections.unmodifiableList(cities);
    }

    @Override
    public String toString() {
        return "Prefecture{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
