package zad2;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import zad2.Wyjatki.BladWykonania;

public class If extends Instrukcja {
    @JsonProperty("warunek")
    public Instrukcja warunek;
    @JsonProperty("blok_prawda")
    private Instrukcja blok_prawda;
    @JsonProperty("blok_falsz")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Instrukcja blok_falsz = null;

    @JsonCreator
    public If(@JsonProperty("warunek") Instrukcja warunek,
              @JsonProperty("blok_prawda") Instrukcja blok_prawda,
              @JsonProperty("blok_falsz") Instrukcja blok_falsz) {
        this.warunek = warunek;
        this.blok_prawda = blok_prawda;
        this.blok_falsz = blok_falsz;
    }

    @Override
    public String toJava() {
        StringBuilder sb = new StringBuilder();

        if (!this.warunek.jestWyrazeniem()) {
            sb.append("throw new BladWykonania(\"Błąd wykonania: warunek instrukcji warunkowej If " +
                      "nie jest wyrażeniem logicznym lub arytmetycznym.\");\n");
            return sb.toString();
        }

        this.warunek.ustawZadeklarowaneZmienne(this.zadeklarowane_zmienne);
        this.blok_prawda.ustawZadeklarowaneZmienne(this.zadeklarowane_zmienne);
        if (this.blok_falsz != null) {
            this.blok_falsz.ustawZadeklarowaneZmienne(this.zadeklarowane_zmienne);
        }

        sb.append("if (");
        if (this.warunek.jestWyrazeniemLogicznymAleNieStalaLogiczna()) {
            sb.append(this.warunek.toJava());
        } else {
            /* Jeśli warunek jest wyrażeniem arytmetycznym lub stałą logiczną,
             * porównujemy go z zerem. */
            sb.append("(").append(this.warunek.toJava()).append(") != 0");
        }
        sb.append(") {\n");

        /* Jeśli blok_prawda lub blok_falsz są wyrażeniem,
        * wypisujemy je na standardowe wyjście. */

        if (this.blok_prawda.jestWyrazeniem()) {
            sb.append("System.out.println(");
        }
        sb.append(this.blok_prawda.toJava());
        if (this.blok_prawda.jestWyrazeniem()) {
            sb.append(")");
        }
        if (this.blok_prawda.jestWyrazeniemLubInstrukcjaPrzypisania()) {
            sb.append(";\n");
        }
        if (this.blok_prawda.jestWyrazeniem()) {
            sb.append("\nwypisany_wynik = true;");
        }
        sb.append("}");

        if (this.blok_falsz != null) {
            sb.append(" else {\n");
            if (this.blok_falsz.jestWyrazeniem()) {
                sb.append("System.out.println(");
            }
            sb.append(this.blok_falsz.toJava());
            if (this.blok_falsz.jestWyrazeniem()) {
                sb.append(")");
            }
            if (this.blok_falsz.jestWyrazeniemLubInstrukcjaPrzypisania()) {
                sb.append(";\n");
            }
            if (this.blok_falsz.jestWyrazeniem()) {
                sb.append("\nwypisany_wynik = true;");
            }
            sb.append("}");
        }
        return sb.toString();
    }

    @Override
    public double wykonaj() throws BladWykonania {
        if (!this.warunek.jestWyrazeniem()) {
            throw new BladWykonania("Błąd wykonania: warunek instrukcji warunkowej " +
                                    "If nie jest wyrażeniem logicznym lub arytmetycznym.\n");
        }
        this.warunek.ustawZmienne(this.zmienne);
        if (this.warunek.wykonaj() != 0) {
            this.blok_prawda.ustawZmienne(this.zmienne);
            return this.blok_prawda.wykonaj();
        } else if (this.blok_falsz != null) {
            this.blok_falsz.ustawZmienne(this.zmienne);
            return this.blok_falsz.wykonaj();
        } else {
            return 0.0;
        }
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
