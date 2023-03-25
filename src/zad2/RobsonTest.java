package zad2;

import org.junit.Test;
import zad2.Wyjatki.BladWykonania;
import zad2.Wyjatki.NieprawidlowyProgram;

import java.io.IOException;

public class RobsonTest {
    @Test
    public static void algorytmEuklidesa() throws BladWykonania {
        Robson robson = new Robson();
        try {
            robson.fromJSON("test/algorytmEuklidesa.json");
            robson.toJSON("test/algorytmEuklidesa.json");
            double wynik = robson.wykonaj();
            System.out.println(wynik);
            robson.toJava("test/algorytmEuklidesa.java");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, NieprawidlowyProgram, BladWykonania {
        if (args.length >= 2) {
            if (args[0].endsWith(".json") && args[1].endsWith(".java")) {
                try {
                    Robson robson = new Robson();
                    robson.fromJSON(args[0]);
                    robson.toJSON(args[0]);
                    double wynik = robson.wykonaj();
                    System.out.println(wynik);
                    robson.toJava(args[1]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                algorytmEuklidesa();
            }
        } else {
            algorytmEuklidesa();
        }
    }
}
