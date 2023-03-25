package zad2;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import zad2.Wyjatki.BladWykonania;
import zad2.WyrazeniaArytmetyczne.*;
import zad2.WyrazeniaLogiczne.*;
import zad2.WyrazeniaLogiczne.Porownania.*;

import java.util.ArrayList;
import java.util.HashMap;

@JsonTypeInfo(use = Id.NAME,
        property = "typ")
@JsonSubTypes({
        @Type(value = Blok.class),
        @Type(value = If.class),
        @Type(value = While.class),
        @Type(value = Przypisanie.class),
        @Type(value = Plus.class),
        @Type(value = Minus.class),
        @Type(value = Razy.class),
        @Type(value = Dzielenie.class),
        @Type(value = And.class),
        @Type(value = Or.class),
        @Type(value = Mniejsze.class, name = "<"),
        @Type(value = Wieksze.class, name = ">"),
        @Type(value = MniejszeLubRowne.class, name = "<="),
        @Type(value = WiekszeLubRowne.class, name = ">="),
        @Type(value = Rowne.class, name = "=="),
        @Type(value = Not.class),
        @Type(value = Liczba.class),
        @Type(value = True.class),
        @Type(value = False.class),
        @Type(value = Zmienna.class),
})

public abstract class Instrukcja {
    @JsonIgnore
    protected HashMap<String, Double> zmienne = new HashMap<>();
    @JsonIgnore
    protected ArrayList<String> zadeklarowane_zmienne = new ArrayList<>();

    public abstract String toJava();

    public void ustawZadeklarowaneZmienne(ArrayList<String> zadeklarowane_zmienne) {
        this.zadeklarowane_zmienne = zadeklarowane_zmienne;
    }

    public abstract double wykonaj() throws BladWykonania;

    public void ustawZmienne(HashMap<String, Double> zmienne) {
        this.zmienne = zmienne;
    }

    public abstract boolean jestLiczbaLubZmienna();

    public abstract boolean jestWyrazeniem();

    public abstract boolean jestWyrazeniemLubInstrukcjaPrzypisania();

    public abstract boolean jestWyrazeniemArytmetycznym();

    public abstract boolean jestWyrazeniemLogicznym();

    public abstract boolean jestWyrazeniemLogicznymAleNieStalaLogiczna();
}
