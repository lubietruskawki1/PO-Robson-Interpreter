package zad2.WyrazeniaArytmetyczne;

import zad2.Wyjatki.BladWykonania;
import zad2.Wyrazenie;

public abstract class WyrazenieArytmetyczne extends Wyrazenie {
    public abstract String toJava();
    public abstract double wykonaj() throws BladWykonania;
    public abstract boolean jestLiczbaLubZmienna();
    public boolean jestWyrazeniemArytmetycznym() {
        return true;
    }
    public boolean jestWyrazeniemLogicznym() {
        return false;
    }
    public boolean jestWyrazeniemLogicznymAleNieStalaLogiczna() {
        return false;
    }
}
