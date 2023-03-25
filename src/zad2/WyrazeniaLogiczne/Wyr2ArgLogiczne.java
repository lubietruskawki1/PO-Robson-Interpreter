package zad2.WyrazeniaLogiczne;

import com.fasterxml.jackson.annotation.JsonProperty;
import zad2.Instrukcja;
import zad2.Wyjatki.BladWykonania;

public abstract class Wyr2ArgLogiczne extends WyrazenieLogiczne {
    @JsonProperty("argument1")
    protected Instrukcja argument1;
    @JsonProperty("argument2")
    protected Instrukcja argument2;

    public Wyr2ArgLogiczne(Instrukcja argument1, Instrukcja argument2) {
        this.argument1 = argument1;
        this.argument2 = argument2;
    }

    protected abstract String znakOperacji();

    @Override
    public String toJava() {
        StringBuilder sb = new StringBuilder();

        if (!this.argument1.jestWyrazeniem() &&
                !this.argument2.jestWyrazeniem()) {
            sb.append("throw new BladWykonania(\"Błąd wykonania: argumenty operacji " +
                      "logicznej nie są wyrażeniami logicznymi lub arytmetycznymi.\");");
            return sb.toString();
        } else if (!this.argument1.jestWyrazeniem()) {
            sb.append("throw new BladWykonania(\"Błąd wykonania: pierwszy argument operacji " +
                      "logicznej nie jest wyrażeniem logicznym lub arytmetycznym.\");");
            return sb.toString();
        } else if (!this.argument2.jestWyrazeniem()) {
            sb.append("throw new BladWykonania(\"Błąd wykonania: drugi argument operacji " +
                      "logicznej nie jest wyrażeniem logicznym lub arytmetycznym.\");");
            return sb.toString();
        }

        this.argument1.ustawZadeklarowaneZmienne(this.zadeklarowane_zmienne);
        this.argument2.ustawZadeklarowaneZmienne(this.zadeklarowane_zmienne);

        sb.append("((");
        if (this.argument1.jestWyrazeniemLogicznymAleNieStalaLogiczna()) {
            sb.append(this.argument1.toJava());
        } else {
            /* Jeśli argument jest wyrażeniem arytmetycznym lub stałą logiczną,
            * porównujemy go z zerem. */
            sb.append("(").append(this.argument1.toJava()).append(") != 0");
        }
        sb.append(") ").append(this.znakOperacji()).append(" (");
        if (this.argument2.jestWyrazeniemLogicznymAleNieStalaLogiczna()) {
            sb.append(this.argument2.toJava());
        } else {
            sb.append("(").append(this.argument2.toJava()).append(") != 0");
        }
        sb.append("))");
        return sb.toString();
    }

    public boolean jestWyrazeniemLogicznymAleNieStalaLogiczna() {
        return true;
    }

    public abstract double wykonaj() throws BladWykonania;
}
