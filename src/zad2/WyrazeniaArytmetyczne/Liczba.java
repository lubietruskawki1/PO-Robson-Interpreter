package zad2.WyrazeniaArytmetyczne;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Liczba extends WyrazenieArytmetyczne {
    @JsonProperty("wartosc")
    private double wartosc;

    @JsonCreator
    public Liczba(@JsonProperty("wartosc")double wartosc) {
        this.wartosc = wartosc;
    }

    public String toJava() {
        return "" + this.wartosc;
    }

    public double wykonaj() {
        return this.wartosc;
    }

    public boolean jestLiczbaLubZmienna() {
        return true;
    }
}
