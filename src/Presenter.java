import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
/**
 * Wypisuje zawartosść podanej do niej ArrayListy jednocześnie relizując
 * obslugę zmiennych znakowych.
 *
 */

public class Presenter {

    Presenter() {}

    public void present(String x, ArrayList<BibtexEntry> E){
        System.out.println("\n");

        for(BibtexEntry bibEntry : E){

            for (int i = 0; i < 121; i++) System.out.print(x);

            String type = bibEntry.type;
            if(type.equals("STRING"))
                continue;
            String keyupp = bibEntry.key;
            double typeOffset = (20 - type.length()) / 2 - 1;
            double keyuppOffset = (100 - keyupp.length()) / 2 - 1;


            //wypisanie lini odstepu
            System.out.print("\n");
            System.out.print(x);
            for (int i = 0; i < 19; i++) System.out.print(" ");
            for (int i = 0; i < 100; i++) System.out.print(" ");
            System.out.print(x);
            System.out.print("\n");

            //wypisanie jednego wiersza
            System.out.print(x);
            for (int i = 0; i <= typeOffset; i++) System.out.print(" ");
            System.out.print(type +" (" + keyupp + ")");
            for (int i = 0; i <= 20-typeOffset-type.length()-3; i++) System.out.print(" ");
            for (int i = 0; i <= keyuppOffset-2; i++) System.out.print(" ");
            for (int i = 0; i <= 100-keyuppOffset-keyupp.length()-3; i++) System.out.print(" ");
            System.out.print(x);

            //linia odstepu
            System.out.print("\n");
            System.out.print(x);
            for (int i = 0; i < 19; i++) System.out.print(" ");
            for (int i = 0; i < 100; i++) System.out.print(" ");
            System.out.print(x);

            System.out.print("\n");


            for(Map.Entry<String, String> mapEntry : bibEntry.Rekordy.entrySet()){

                //obsluga zmiennych znnakowych
                String key = mapEntry.getKey();
                String value = mapEntry.getValue();

                HashMap Str = BibtexParser.StrMap;
                for(Map.Entry<String, String> StrEntry : BibtexParser.StrMap.entrySet()){
                    String match = StrEntry.getKey();
                    String change = StrEntry.getValue();
                    if(value!=null) {
                        String result[] = value.split(match);
                        if(value.equals(match)){
                            value=change;
                            continue;
                        }
                        if(result.length>1) {

                            ArrayList<String> list = new ArrayList<>(Arrays.asList(result));
                            value ="";

                            for(int i = 0; i<result.length-1; i++){
                                value=value+result[i]+change;
                            }
                            value=value+result[result.length-1];


                        }
                    }



                }

                if(key != null && value != null) {
                    double keyOffset = (20 - key.length()) / 2 - 1;
                    double valOffset = (100 - value.length()) / 2 - 1;

                    for (int i = 0; i < 120; i++) System.out.print(x);
                    System.out.print(x); // <- wypisano górną linie

                    //wypisanie lini odstepu
                    System.out.print("\n");
                    System.out.print(x);
                    for (int i = 0; i < 19; i++) System.out.print(" ");
                    System.out.print(x);
                    for (int i = 0; i < 99; i++) System.out.print(" ");
                    System.out.print(x);
                    System.out.print("\n");

                    //wypisanie jednego wiersza
                    System.out.print(x);
                    for (int i = 0; i <= keyOffset; i++) System.out.print(" ");
                    System.out.print(key);
                    for (int i = 0; i <= 20-keyOffset-key.length()-3; i++) System.out.print(" ");
                    System.out.print(x);
                    for (int i = 0; i <= valOffset; i++) System.out.print(" ");
                    System.out.print(value);
                    for (int i = 0; i <= 100-valOffset-value.length()-3; i++) System.out.print(" ");
                    System.out.print(x);

                    //linia odstepu
                    System.out.print("\n");
                    System.out.print(x);
                    for (int i = 0; i < 19; i++) System.out.print(" ");
                    System.out.print(x);
                    for (int i = 0; i < 99; i++) System.out.print(" ");
                    System.out.print(x);

                    System.out.print("\n");
                }


            }
            for (int i = 0; i < 121; i++) System.out.print(x);
            for(int i=0; i<6; i++) System.out.println("\n");
        }
    }
}
