package zad2;

import zad2.Wyjatki.BladWykonania;

public abstract class Wyrazenie extends Instrukcja {
    public abstract String toJava();
    public abstract double wykonaj() throws BladWykonania;
    public abstract boolean jestLiczbaLubZmienna();
    public boolean jestWyrazeniem() {
        return true;
    }
    public boolean jestWyrazeniemLubInstrukcjaPrzypisania() {
        return true;
    }
    public abstract boolean jestWyrazeniemArytmetycznym();
    public abstract boolean jestWyrazeniemLogicznym();
    public abstract boolean jestWyrazeniemLogicznymAleNieStalaLogiczna();
}