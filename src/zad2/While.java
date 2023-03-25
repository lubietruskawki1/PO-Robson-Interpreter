package zad2;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import zad2.Wyjatki.BladWykonania;

public class While extends Instrukcja {
    @JsonProperty("warunek")
    private Instrukcja warunek;
    @JsonProperty("blok")
    private Instrukcja blok;

    @JsonCreator
    public While(@JsonProperty("warunek") Instrukcja warunek, @JsonProperty("blok") Instrukcja blok) {
        this.warunek = warunek;
        this.blok = blok;
    }

    @Override
    public String toJava() {
        StringBuilder sb = new StringBuilder();

        if (!this.warunek.jestWyrazeniem()) {
            sb.append("throw new BladWykonania(\"Błąd wykonania: warunek pętli While " +
                      "nie jest wyrażeniem logicznym lub arytmetycznym.\");");
            return sb.toString();
        }

        this.warunek.ustawZadeklarowaneZmienne(this.zadeklarowane_zmienne);
        this.blok.ustawZadeklarowaneZmienne(this.zadeklarowane_zmienne);

        sb.append("while (");
        if (this.warunek.jestWyrazeniemLogicznymAleNieStalaLogiczna()) {
            sb.append(this.warunek.toJava());
        } else {
            /* Jeśli warunek jest wyrażeniem arytmetycznym lub stałą logiczną,
             * porównujemy go z zerem. */
            sb.append("(").append(this.warunek.toJava()).append(") != 0");
        }
        sb.append(") {\n");
        sb.append(this.blok.toJava()).append("}");
        return sb.toString();
    }

    @Override
    public double wykonaj() throws BladWykonania {
        if (!this.warunek.jestWyrazeniem()) {
            throw new BladWykonania("Błąd wykonania: warunek pętli While " +
                                    "nie jest wyrażeniem logicznym lub arytmetycznym.\n");
        }
        this.warunek.ustawZmienne(this.zmienne);
        while (this.warunek.wykonaj() != 0) {
            this.blok.ustawZmienne(this.zmienne);
            this.blok.wykonaj();
            this.warunek.ustawZmienne(this.zmienne);
        }
        return 0.0;
    }

    public boolean jestLiczbaLubZmienna() {
        return false;
    }

    public boolean jestWyrazeniem() {
        return false;
    }

    public boolean jestWyrazeniemLubInstrukcjaPrzypisania() {
        return false;
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
