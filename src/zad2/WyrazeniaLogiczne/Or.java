package zad2.WyrazeniaLogiczne;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import zad2.Instrukcja;
import zad2.Wyjatki.BladWykonania;

public class Or extends Wyr2ArgLogiczne {
    @JsonCreator
    public Or(@JsonProperty("argument1") Instrukcja argument1,
              @JsonProperty("argument2") Instrukcja argument2) {
        super(argument1, argument2);
    }

    public String znakOperacji() {
        return "||";
    }

    @Override
    public double wykonaj() throws BladWykonania {
        if (!this.argument1.jestWyrazeniem() && !this.argument2.jestWyrazeniem()) {
            throw new BladWykonania("Błąd wykonania: argumenty operacji " +
                                    "logicznej Or nie są wyrażeniami logicznymi lub arytmetycznymi.\n");
        } else if (!this.argument1.jestWyrazeniem()) {
            throw new BladWykonania("Błąd wykonania: pierwszy argument operacji " +
                                    "logicznej Or nie jest wyrażeniem logicznym lub arytmetycznym.\n");
        } else if (!this.argument2.jestWyrazeniem()) {
            throw new BladWykonania("Błąd wykonania: drugi argument operacji " +
                                    "logicznej Or nie jest wyrażeniem logicznym lub arytmetycznym.\n");
        }
        this.argument1.ustawZmienne(this.zmienne);
        this.argument2.ustawZmienne(this.zmienne);
        if (this.argument1.wykonaj() != 0 || this.argument2.wykonaj() != 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
