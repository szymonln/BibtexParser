import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.zip.DataFormatException;
/**
 * Zwraca AtrayLiostę HashMap z wpisami bazy Bibtex.
 * Używa przekazanej mu ścieżki dostępu do pliku z klasy Main.
 * Tworzy czytniki pliku w konstruktorze.
 *
 * Należy podać argumenty, kolejno: ścieżkę do pliku, parametry wyszukiwania i znak
 * do tworzenia tabeli dla klasy Presenter.
 */
public class BibtexParser  {
    String fileName;
    BibtexParser(String name) throws FileNotFoundException {
        fileName=name;

        reader = new FileReader(fileName);
        bReader = new BufferedReader(reader);
    }

    FileReader reader;
    BufferedReader bReader;

    /**
     * Metoda ta omija komentarze szukając wpisu z @ na poczatku
     * @return textline pierwsza linia własciwego wpisu
     * @throws IOException
     */

    public String skipComments() throws IOException{
        String textline = "";
        while(textline != null && !Pattern.matches("@.*", textline)) {
            textline = bReader.readLine();
        }
        return textline;
    }

    static HashMap<String, String> StrMap = new HashMap<>();

    /**
     * Parsuje plik wejściowy jednocześnie wyłuskując zmienne znakowe
     * @return ArrayList BibtexDB Baza wpisów
     * @throws IOException gdy plik nie istnieje
     */
    public ArrayList<BibtexEntry> parse() throws IOException{
        //szukam pierwszego wpisu z @ na początku
        String line = " ";
        String[] to_remove = {" ", "  ", ""}; //do użycia w metodzie remove w ArrayList

        ArrayList<BibtexEntry> BibtexDB = new ArrayList<BibtexEntry>();
        while(line != null) {
            line=skipComments();
            //System.out.println(line);
            ArrayList<String> trimmedList = new ArrayList<String>();
            int it = 0;
            int iter = 0;
            if(line == null) break;
            BibtexEntry entr = null; // tworzę nowy obiekt

            while(!Pattern.matches("}", line) && !line.equals("") && !Pattern.matches("@preamble.*",line) && !Pattern.matches("@comment.*",line)){ //czy koniec wpisu

                String result[] = line.split("[@{=,\"}]");
                ArrayList<String> list = new ArrayList<>(Arrays.asList(result));
                list.removeAll(Arrays.asList(to_remove)); //utworzone tablice z polami i wartościami do wpisania do obiektu, nastepnie usuwam wartości bedace pustymi ciagami znakow

                //usuwanie spacji wiodących
                String trimmed;
                trimmedList.clear();


                for (String a : list) {
                    trimmed = a.trim();
                    trimmedList.add(trimmed);
                }


                String key, value;   //budowanie obiektu i dodawanie do hashmapy wartości
                key = trimmedList.get(0);
                value = "";




                for (int i = 1; i < trimmedList.size(); ++i) {
                    value = value + trimmedList.get(i);
                }
                if (it == 0) {
                    entr = new BibtexEntry(key, value);
                    entr.generateMap();
                } else {
                    if(entr.Rekordy.containsKey(key) || entr.Optional.containsKey(key)) //omijanie pól ignorowanych
                        entr.Rekordy.put(key, value);   //nadpisze nulle
                }

                //budowanie mapy ze stringami
                if (key.equals("STRING")){
                    value = "";
                    key=trimmedList.get(1);
                    for (int i = 2; i < trimmedList.size(); ++i) {
                        value = value + trimmedList.get(i);
                    }
                    StrMap.put(key, value);
                    //System.out.println(key + "    " + value); //debugging
                }

                line = bReader.readLine();
                it++;

            }
            if(entr != null) {
                try {
                    entr.problem();
                } catch (DataFormatException e) {
                    e.printStackTrace();
                }
                BibtexDB.add(entr);
            }



        }
        bReader.close();
        reader.close();

        return BibtexDB;
    }




}