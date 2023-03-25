package zad2.WyrazeniaLogiczne;

import zad2.Wyjatki.BladWykonania;
import zad2.Wyrazenie;

public abstract class WyrazenieLogiczne extends Wyrazenie {
    public abstract String toJava();
    public abstract double wykonaj() throws BladWykonania;
    public boolean jestLiczbaLubZmienna() {
        return false;
    }
    public boolean jestWyrazeniemArytmetycznym() {
        return false;
    }
    public boolean jestWyrazeniemLogicznym() {
        return true;
    }
    public abstract boolean jestWyrazeniemLogicznymAleNieStalaLogiczna();
}
