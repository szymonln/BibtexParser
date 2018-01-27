import java.util.HashMap;
import java.util.zip.DataFormatException;
/**
 * Metoda ta generuje pola hashmapy dla konkretnego wpisu BibtexEntry
 * aby umożliwić sprawdzanie poprawności pól oraz obecność pol wymaganych.
 *
 * <p>
 * Posiada metodę problem() któa generuje wyjątek dla niepełnego wpisu.
 */

public class BibtexEntry {

    String type;
    String key;
    HashMap<String, String> Rekordy  = new HashMap<String, String>();
    HashMap<String, String> Optional  = new HashMap<String, String>();

    BibtexEntry(String type, String key){
        this.type = type;
        this.key = key;
    }

    /**
     * Tworzy Mapy dla danego typu wpisu
     * aby umożliwić obsługę pól opcjonalnych, wymaganych i ignorowanych.
     */
    public void generateMap(){
        if (this.type.equals("ARTICLE")){
            Rekordy.put("author",null);
            Rekordy.put("title",null);
            Rekordy.put("journal",null);
            Rekordy.put("year",null);
            Optional.put("volume",null);
            Optional.put("number",null);
            Optional.put("pages",null);
            Optional.put("month",null);
            Optional.put("note",null);
            Optional.put("key",null);

        }
        if (this.type.equals("BOOK")){
            Rekordy.put("author",null);
            Rekordy.put("editor",null);
            Rekordy.put("title",null);
            Rekordy.put("publisher",null);
            Rekordy.put("year",null);
            Optional.put("volume",null);
            Optional.put("series",null);
            Optional.put("address",null);
            Optional.put("edition",null);
            Optional.put("month",null);
            Optional.put("note",null);
            Optional.put("key",null);

        }
        if (type.equals("INPROCEEDINGS") || type.equals("CONFERENCE") || type.equals("PROCEEDINGS")){
            Rekordy.put("author",null);
            Rekordy.put("title",null);
            Rekordy.put("publisher",null);
            Rekordy.put("year",null);
            Optional.put("editor",null);
            Optional.put("volume",null);
            Optional.put("number",null);
            Optional.put("series",null);
            Optional.put("pages",null);
            Optional.put("address",null);
            Optional.put("month",null);
            Optional.put("organization", null);
            Optional.put("publisher",null);
            Optional.put("note",null);
            Optional.put("key",null);
            Optional.put("booktitle",null);
        }
        if (type.equals("BOOKLET")){
            Rekordy.put("title",null);
            Optional.put("author",null);
            Optional.put("howpublished",null);
            Optional.put("address",null);
            Optional.put("month",null);
            Optional.put("year",null);
            Optional.put("note",null);
            Optional.put("key",null);


        }
        if (type.equals("INBOOK")){
            Rekordy.put("author",null);
            Rekordy.put("editor",null);
            Rekordy.put("title",null);
            Rekordy.put("chapter",null);
            Rekordy.put("pages",null);
            Optional.put("volume",null);
            Optional.put("number",null);
            Optional.put("series",null);
            Optional.put("pages",null);
            Optional.put("address",null);
            Optional.put("month",null);
            Optional.put("type", null);
            Optional.put("chapter",null);
            Optional.put("note",null);
            Optional.put("key",null);
        }
        if (type.equals("INCOLLECTION")){
            Rekordy.put("author",null);
            Rekordy.put("title",null);
            Rekordy.put("booktitle", null);
            Rekordy.put("publisher", null);
            Rekordy.put("year",null);
            Optional.put("editor",null);
            Optional.put("volume",null);
            Optional.put("number",null);
            Optional.put("series",null);
            Optional.put("pages",null);
            Optional.put("address",null);
            Optional.put("month",null);
            Optional.put("organization", null);
            Optional.put("publisher",null);
            Optional.put("note",null);
            Optional.put("key",null);
        }
        if (type.equals("MANUAL")){
            Rekordy.put("title",null);
            Optional.put("edition",null);
            Optional.put("year",null);
            Optional.put("address",null);
            Optional.put("author",null);
            Optional.put("organization", null);
            Optional.put("month",null);
            Optional.put("note",null);
            Optional.put("key",null);
        }
        if (type.equals("MASTERTHESIS")){
            Rekordy.put("author",null);
            Rekordy.put("title",null);
            Rekordy.put("school",null);
            Rekordy.put("year",null);
            Optional.put("type",null);
            Optional.put("address",null);
            Optional.put("month",null);
            Optional.put("note",null);
            Optional.put("key",null);
        }
        if (type.equals("PHDTHESIS")){
            Rekordy.put("author",null);
            Rekordy.put("title",null);
            Rekordy.put("school",null);
            Rekordy.put("year",null);
            Optional.put("type",null);
            Optional.put("address",null);
            Optional.put("month",null);
            Optional.put("note",null);
            Optional.put("key",null);
        }
        if (type.equals("TECHREPORT")){
            Rekordy.put("author",null);
            Rekordy.put("title",null);
            Rekordy.put("institution",null);
            Rekordy.put("year",null);
            Optional.put("editor",null);
            Optional.put("volume",null);
            Optional.put("number",null);
            Optional.put("series",null);
            Optional.put("address",null);
            Optional.put("month",null);
            Optional.put("organization", null);
            Optional.put("publisher",null);
            Optional.put("note",null);
            Optional.put("key",null);
        }
        if (type.equals("UNPUBLISHED")){
            Rekordy.put("author",null);
            Rekordy.put("title",null);
            Rekordy.put("note",null);
            Optional.put("month",null);
            Optional.put("year",null);
            Optional.put("key",null);
        }



    }

    /**
     *
     * Wywołana standardowo przez Parser dl akażdego nowego wpisu
     * sprawdza poprawność jego pól
     * @return true/false true - gdy istnieje problem, false w przeciwnym wypadku
     * @throws DataFormatException gdy istnieje problem
     */

    public boolean problem() throws DataFormatException {
        for(String value : Rekordy.values())
            if(value == null) {
                throw new DataFormatException();
            }

        return false;
    }






}

