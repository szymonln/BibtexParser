import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Należy podać argumenty: ścieżkę do pliku, dane do filtrowania,
 * znak do tworzenia tabeli dla klasy Presenter.
 *
 * Domyślnie wypisuje całą bazę wpisów.
 */
public class Main {
    public static void main(String [] args) throws IOException {

        if(args.length==0){
            BufferedReader br = new BufferedReader(new FileReader("help.txt"));
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            System.exit(0);
        }

        BibtexParser Parser;
        String name = args[0];
        Parser = new BibtexParser(name);

        String x = args[args.length-1];

        ArrayList<BibtexEntry> BibtexDB = Parser.parse();
        //System.out.println(BibtexDB);
        if(args.length==2) {
            Presenter OUT = new Presenter(); //presenter radzi sobie ze zmiennymi napisowymi
            OUT.present(x, BibtexDB);
        }

        //wywoływanie filtrów
        ArrayList <String> categories = new ArrayList();
        categories.add("ARTICLE");
        categories.add("BOOK");
        categories.add("INPROCEEDINGS");
        categories.add("PROCEEDINGS");
        categories.add("BOOKLET");
        categories.add("INBOOK");
        categories.add("INCOLLECTION");
        categories.add("MANUAL");
        categories.add("MASTERTHESIS");
        categories.add("PHDTHESIS");
        categories.add("TECHREPORT");
        categories.add("MISC");
        categories.add("UNPUBLISHED");

        for(int i=1; i<args.length-1; i++){
            if(categories.contains(args[i])){
                CategoryFilter CatF = new CategoryFilter(args[i],BibtexDB);
                ArrayList Cfiltered = CatF.filter();
                Presenter OUT = new Presenter(); //presenter radzi sobie ze zmiennymi napisowymi
                OUT.present(x, Cfiltered);
            }
            else{
                AuthorFilter AFilter = new AuthorFilter(args[i],BibtexDB);
                ArrayList Filtered = AFilter.filter();
                Presenter OUT = new Presenter(); //presenter radzi sobie ze zmiennymi napisowymi
                OUT.present(x, Filtered);
            }
        }



    }
}
