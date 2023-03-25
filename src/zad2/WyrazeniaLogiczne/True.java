package zad2.WyrazeniaLogiczne;

import com.fasterxml.jackson.annotation.JsonCreator;
import zad2.WyrazeniaLogiczne.StalaLogiczna;

public class True extends StalaLogiczna {
    @JsonCreator
    public True() {}

    @Override
    public String toJava() {
        return "1";
    }

    @Override
    public double wykonaj() {
        return 1;
    }
}
