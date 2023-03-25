package zad2.WyrazeniaLogiczne.Porownania;

import com.fasterxml.jackson.annotation.JsonProperty;
import zad2.Wyjatki.BladWykonania;
import zad2.Instrukcja;
import zad2.WyrazeniaLogiczne.WyrazenieLogiczne;

public abstract class Porownanie extends WyrazenieLogiczne {
    @JsonProperty("argument1")
    protected Instrukcja argument1;
    @JsonProperty("argument2")
    protected Instrukcja argument2;

    public Porownanie(Instrukcja argument1, Instrukcja argument2) {
        this.argument1 = argument1;
        this.argument2 = argument2;
    }

    protected abstract String znakOperacji();

    @Override
    public String toJava() {
        StringBuilder sb = new StringBuilder();

        if (!this.argument1.jestWyrazeniemArytmetycznym() &&
                !this.argument2.jestWyrazeniemArytmetycznym()) {
            sb.append("throw new BladWykonania(\"Błąd wykonania: argumenty operacji " +
                      "porównania nie są wyrażeniami arytmetycznymi.\");");
            return sb.toString();
        } else if (!this.argument1.jestWyrazeniemArytmetycznym()) {
            sb.append("throw new BladWykonania(\"Błąd wykonania: pierwszy argument operacji " +
                      "porównania nie jest wyrażeniem arytmetycznym.\");");
            return sb.toString();
        } else if (!this.argument2.jestWyrazeniemArytmetycznym()) {
            sb.append("throw new BladWykonania(\"Błąd wykonania: drugi argument operacji " +
                      "porównania nie jest wyrażeniem arytmetycznym.\");");
            return sb.toString();
        }

        this.argument1.ustawZadeklarowaneZmienne(this.zadeklarowane_zmienne);
        this.argument2.ustawZadeklarowaneZmienne(this.zadeklarowane_zmienne);

        /* Dodajemy nawiasy jeśli argument nie jest liczbą lub zmienną. */
        if (this.argument1.jestLiczbaLubZmienna()) {
            sb.append(this.argument1.toJava());
        } else {
            sb.append("(").append(this.argument1.toJava()).append(")");
        }
        sb.append(" ").append(this.znakOperacji()).append(" ");
        if (this.argument2.jestLiczbaLubZmienna()) {
            sb.append(this.argument2.toJava());
        } else {
            sb.append("(").append(this.argument2.toJava()).append(")");
        }
        return sb.toString();
    }

    public abstract double wykonaj() throws BladWykonania;

    public boolean jestWyrazeniemLogicznymAleNieStalaLogiczna() {
        return true;
    }
}
