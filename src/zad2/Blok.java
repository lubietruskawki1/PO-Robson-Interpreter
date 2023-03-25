package zad2;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import zad2.Wyjatki.BladWykonania;

import java.util.ArrayList;

public class Blok extends Instrukcja {
    @JsonProperty("instrukcje")
    private ArrayList<Instrukcja> instrukcje;

    @JsonCreator
    public Blok(@JsonProperty("instrukcje")ArrayList<Instrukcja> instrukcje) {
        this.instrukcje = new ArrayList<>(instrukcje);
    }

    @Override
    public String toJava() {
        if (this.instrukcje.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Instrukcja instrukcja : this.instrukcje) {
            instrukcja.ustawZadeklarowaneZmienne(this.zadeklarowane_zmienne);
            if (instrukcja.jestWyrazeniem()) {
                sb.append("System.out.println(");
            }
            if (instrukcja.jestWyrazeniemLogicznymAleNieStalaLogiczna()) {
                sb.append("((").append(instrukcja.toJava()).append(") ? 1.0 : 0.0)");
            } else {
                sb.append(instrukcja.toJava());
            }
            if (instrukcja.jestWyrazeniem()) {
                sb.append(")");
            }
            if (instrukcja.jestWyrazeniemLubInstrukcjaPrzypisania()) {
                sb.append(";");
            }
            if (instrukcja.jestWyrazeniem()) {
                sb.append("\nwypisany_wynik = true;");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public double wykonaj() throws BladWykonania {
        if (this.instrukcje.isEmpty()) {
            return 0;
        }
        for (int i = 0; i < this.instrukcje.size() - 1; i++) {
            this.instrukcje.get(i).ustawZmienne(this.zmienne);
            this.instrukcje.get(i).wykonaj();
        }
        this.instrukcje.get(this.instrukcje.size() - 1).ustawZmienne(this.zmienne);
        return this.instrukcje.get(this.instrukcje.size() - 1).wykonaj();
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
