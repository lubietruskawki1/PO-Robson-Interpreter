package zad2;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import zad2.Wyjatki.BladWykonania;

public class Przypisanie extends Instrukcja {
    @JsonProperty("nazwa")
    private String nazwa;
    @JsonProperty("wartosc")
    private Instrukcja wartosc;

    @JsonCreator
    public Przypisanie(@JsonProperty("nazwa")String nazwa, @JsonProperty("wartosc") Instrukcja wartosc) {
        this.nazwa = nazwa;
        this.wartosc = wartosc;
    }

    @Override
    public String toJava() {
        this.wartosc.ustawZadeklarowaneZmienne(this.zadeklarowane_zmienne);
        StringBuilder sb = new StringBuilder();
        if (!this.zadeklarowane_zmienne.contains(this.nazwa)) {
            this.zadeklarowane_zmienne.add(this.nazwa);
        }
        sb.append(this.nazwa).append(" = ");
        if (this.wartosc.jestWyrazeniemLogicznymAleNieStalaLogiczna()) {
            sb.append("((").append(this.wartosc.toJava()).append(") ? 1.0 : 0.0)");
        } else {
            sb.append(this.wartosc.toJava());
        }
        return sb.toString();
    }

    @Override
    public double wykonaj() throws BladWykonania {
        this.wartosc.ustawZmienne(this.zmienne);
        double wartosc_po_wykonaniu = this.wartosc.wykonaj();
        this.zmienne.put(this.nazwa, wartosc_po_wykonaniu);
        return wartosc_po_wykonaniu;
    }

    public boolean jestLiczbaLubZmienna() {
        return false;
    }

    public boolean jestWyrazeniem() {
        return false;
    }

    public boolean jestWyrazeniemLubInstrukcjaPrzypisania() {
        return true;
    }

    public boolean jestWyrazeniemArytmetycznym() {
        return false;
    }

    public boolean jestWyrazeniemLogicznym() {
        return false;
    }

    public boolean jestWyrazeniemLogicznymAleNieStalaLogiczna() {
        return false;
    }
}
