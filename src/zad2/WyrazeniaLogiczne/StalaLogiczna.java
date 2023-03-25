package zad2.WyrazeniaLogiczne;

public abstract class StalaLogiczna extends WyrazenieLogiczne {
    public abstract String toJava();
    public abstract double wykonaj();
    public boolean jestWyrazeniemLogicznymAleNieStalaLogiczna() {
        return false;
    }
}
