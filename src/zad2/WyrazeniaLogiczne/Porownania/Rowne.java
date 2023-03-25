package zad2.WyrazeniaLogiczne.Porownania;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import zad2.Wyjatki.BladWykonania;
import zad2.Instrukcja;

@JsonTypeName("==")
public class Rowne extends Porownanie {
    @JsonCreator
    public Rowne(@JsonProperty("argument1") Instrukcja argument1,
                 @JsonProperty("argument2") Instrukcja argument2) {
        super(argument1, argument2);
    }

    protected String znakOperacji() {
        return "==";
    }

    @Override
    public double wykonaj() throws BladWykonania {
        if (!this.argument1.jestWyrazeniemArytmetycznym() &&
                !this.argument2.jestWyrazeniemArytmetycznym()) {
            throw new BladWykonania("Błąd wykonania: argumenty operacji " +
                                    "porównania == nie są wyrażeniami arytmetycznymi.\n");
        } else if (!this.argument1.jestWyrazeniemArytmetycznym()) {
            throw new BladWykonania("Błąd wykonania: pierwszy argument operacji " +
                                    "porównania == nie jest wyrażeniem arytmetycznym.\n");
        } else if (!this.argument2.jestWyrazeniemArytmetycznym()) {
            throw new BladWykonania("Błąd wykonania: drugi argument operacji " +
                                    "porównania == nie jest wyrażeniem arytmetycznym.\n");
        }
        this.argument1.ustawZmienne(this.zmienne);
        this.argument2.ustawZmienne(this.zmienne);
        if (this.argument1.wykonaj() == this.argument2.wykonaj()) {
            return 1;
        } else {
            return 0;
        }
    }
}