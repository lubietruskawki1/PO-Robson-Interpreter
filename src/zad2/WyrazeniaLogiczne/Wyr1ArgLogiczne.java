package zad2.WyrazeniaLogiczne;

import com.fasterxml.jackson.annotation.JsonProperty;
import zad2.Instrukcja;
import zad2.Wyjatki.BladWykonania;

public abstract class Wyr1ArgLogiczne extends WyrazenieLogiczne {
    @JsonProperty("argument")
    protected Instrukcja argument;

    public Wyr1ArgLogiczne(Instrukcja argument) {
        this.argument = argument;
    }

    public abstract String toJava();

    public abstract double wykonaj() throws BladWykonania;

    public boolean jestWyrazeniemLogicznymAleNieStalaLogiczna() {
        return true;
    }
}
