/* Założenia:
* - Wyrażenia arytmetyczne to Plus, Minus, Razy, Dzielenie, Liczba oraz Zmienna.
* - Wyrażenia logiczne to And, Or, Not, <, >, <=, >=, ==, True oraz False.
* - Wyrażenia to wyrażenia arytmetyczne i logiczne.
* - Jako argumenty operacji arytmetycznych program przyjmuje wyrażenia arytmetyczne, zgodnie z
*   treścią zadania.
* - Jako argumenty operacji And, Or i Not program przyjmuje wyrażenia.
* - Jako argumenty operacji porównania (<, >, <=, >=, ==) program akceptuje wyrażenia arytmetyczne,
*   zgodnie z treścią zadania.
* - Jako warunek pętli While lub instrukcji warunkowej If program akceptuje wyrażenie, zgodnie z
*   treścią zadania.
* - We wszystkich pozostałych przypadkach program akceptuje dowolną Instrukcję.
* - Jeśli argument danej instrukcji jest nieodpowiedniego typu, program zwraca BladWykonania.
* - BladWykonania jest rownież zwracany podczas próby dzielenia przez zero.
* - Wszystkie zmienne są typu double, jeśli wartością przypisywaną do zmiennej jest wyrażenie
*   logiczne, zmienna przyjmuje wartość 1.0 (gdy wyrażenie jest prawdziwe) lub 0.0 (wpp).
*
* Uwaga do toJava:
* Program utworzony przez toJava po wywołaniu powinien wypisać na standardowe wyjście tę samą wartość,
* co metoda wykonaj. Skoro metoda wykonaj może zwracać BladWykonania, to program stworzony przez
* toJava również wyrzuca wyjątek w tych samych miejscach, co jego odpowiednik z wykonaj.
* */

package zad2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import zad2.Wyjatki.BladWykonania;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Robson {
    private Instrukcja instrukcja = null;

    public void fromJSON(String filename) throws IOException {
        ObjectMapper object_mapper = new ObjectMapper();
        try {
            File file = new File(filename);
            instrukcja = object_mapper.readValue(file, Instrukcja.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void toJSON(String filename) throws IOException {
        ObjectMapper object_mapper = new ObjectMapper();
        object_mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        object_mapper.writeValue(new File(filename), instrukcja);
    }

    public void toJava(String filename) throws IOException {
        FileWriter file_writer = new FileWriter(filename);
        /* W celu utworzenia w pliku klasy o odpowiedniej nazwie należy usunąć
        * końcówkę .java z nazwy pliku lub ścieżki. */
        String class_name = filename.substring(0, filename.length() - ".java".length());
        /* Jeśli filename to ścieżka, należy usunąć wszystko poza nazwą znajdującą
        * się po ostatnim ukośniku. */
        int last_occurrence_of_slash = class_name.lastIndexOf("/");
        if (last_occurrence_of_slash != -1) {
            class_name = class_name.substring(last_occurrence_of_slash);
        }
        file_writer.write("public class " + class_name + " {\n");
        file_writer.write("public static class BladWykonania extends Exception {\n");
        file_writer.write("public BladWykonania(String errorMessage) {\n");
        file_writer.write("super(errorMessage);\n");
        file_writer.write("}\n}\n");
        file_writer.write("public static void main(String[] args) {\n");
        String toJava = instrukcja.toJava();
        /* Po wykonaniu toJava w tablicy zadeklarowane_zmienne znajdują się
        * wszystkie zmienne, które pojawiają się w programie, deklarujemy je
        * w kodzie javowym na zero. */
        for (String zmienna : instrukcja.zadeklarowane_zmienne) {
            file_writer.write("double " + zmienna + " = 0;\n");
        }
        file_writer.write("boolean wypisany_wynik = false;\n");
        file_writer.write(toJava);
        file_writer.write("if (!wypisany_wynik) System.out.println(0);\n");
        file_writer.write("}\n}");
        file_writer.close();
    }

    public double wykonaj() throws BladWykonania {
        return instrukcja.wykonaj();
    }
}
