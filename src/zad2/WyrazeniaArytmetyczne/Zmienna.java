package zad2.WyrazeniaArytmetyczne;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Zmienna extends WyrazenieArytmetyczne {
    @JsonProperty("nazwa")
    private String nazwa;

    @JsonCreator
    public Zmienna(@JsonProperty("nazwa")String nazwa) {
        this.nazwa = nazwa;
    }

    @Override
    public String toJava() {
        if (!this.zadeklarowane_zmienne.contains(this.nazwa)) {
            this.zadeklarowane_zmienne.add(this.nazwa);
        }
        return this.nazwa;
    }

    @Override
    public double wykonaj() {
        return this.zmienne.getOrDefault(this.nazwa, 0.0);
    }

    public boolean jestLiczbaLubZmienna() {
        return true;
    }
}
