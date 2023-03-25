package zad2.WyrazeniaLogiczne;

import com.fasterxml.jackson.annotation.JsonCreator;
import zad2.WyrazeniaLogiczne.StalaLogiczna;

public class False extends StalaLogiczna {
    @JsonCreator
    public False() {}

    @Override
    public String toJava() {
        return "0";
    }

    @Override
    public double wykonaj() {
        return 0;
    }
}
