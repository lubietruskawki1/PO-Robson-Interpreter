package zad2.WyrazeniaArytmetyczne;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import zad2.Wyjatki.BladWykonania;
import zad2.Instrukcja;

public class Dzielenie extends Wyr2ArgArytmetyczne {
    @JsonCreator
    public Dzielenie(@JsonProperty("argument1") Instrukcja argument1,
                     @JsonProperty("argument2") Instrukcja argument2) {
        super(argument1, argument2);
    }

    protected String znakOperacji() {
        return "/";
    }

    @Override
    public double wykonaj() throws BladWykonania {
        if (!this.argument1.jestWyrazeniemArytmetycznym() &&
                !this.argument2.jestWyrazeniemArytmetycznym()) {
            throw new BladWykonania("Błąd wykonania: argumenty operacji " +
                                    "arytmetycznej Dzielenie nie są wyrażeniami arytmetycznymi.\n");
        } else if (!this.argument1.jestWyrazeniemArytmetycznym()) {
            throw new BladWykonania("Błąd wykonania: pierwszy argument operacji " +
                                    "arytmetycznej Dzielenie nie jest wyrażeniem arytmetycznym.\n");
        } else if (!this.argument2.jestWyrazeniemArytmetycznym()) {
            throw new BladWykonania("Błąd wykonania: drugi argument operacji " +
                                    "arytmetycznej Dzielenie nie jest wyrażeniem arytmetycznym.\n");
        }

        this.argument1.ustawZmienne(this.zmienne);
        this.argument2.ustawZmienne(this.zmienne);
        double wartosc_zwracana_przez_drugi_argument = this.argument2.wykonaj();
        if (wartosc_zwracana_przez_drugi_argument == 0) {
            throw new BladWykonania( "Błąd wykonania: dzielenie przez zero.\n");
        }
        return this.argument1.wykonaj() / wartosc_zwracana_przez_drugi_argument;
    }
}
