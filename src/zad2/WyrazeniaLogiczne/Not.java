package zad2.WyrazeniaLogiczne;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import zad2.Instrukcja;
import zad2.Wyjatki.BladWykonania;

public class Not extends Wyr1ArgLogiczne {
    @JsonCreator
    public Not(@JsonProperty("argument") Instrukcja argument) {
        super(argument);
    }

    @Override
    public String toJava() {
        StringBuilder sb = new StringBuilder();

        if (!this.argument.jestWyrazeniem()) {
            sb.append("throw new BladWykonania(\"Błąd wykonania: argument operacji logicznej " +
                      "Not nie jest wyrażeniem logicznym lub arytmetycznym.\");");
            return sb.toString();
        }

        this.argument.ustawZadeklarowaneZmienne(this.zadeklarowane_zmienne);
        if (this.argument.jestWyrazeniemLogicznymAleNieStalaLogiczna()) {
            sb.append("!(").append(this.argument.toJava()).append(")");
        } else {
            /* Jeśli argument jest wyrażeniem arytmetycznym lub stałą logiczną,
             * porównujemy go z zerem. */
            sb.append("!((").append(this.argument.toJava()).append(") != 0)");
        }
        return sb.toString();
    }

    @Override
    public double wykonaj() throws BladWykonania {
        if (!this.argument.jestWyrazeniem()) {
            throw new BladWykonania("Błąd wykonania: argument operacji logicznej " +
                                    "Not nie jest wyrażeniem logicznym lub arytmetycznym.\n");
        }
        this.argument.ustawZmienne(this.zmienne);
        if (this.argument.wykonaj() != 0) {
            return 0;
        } else {
            return 1;
        }
    }
}
