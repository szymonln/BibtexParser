import java.util.ArrayList;
import java.util.Map;

public class CategoryFilter implements Filter {
    String check;
    ArrayList<BibtexEntry> E;

    CategoryFilter(String check, ArrayList<BibtexEntry> E){
        this.check = check;
        this.E = E;
    }

    public ArrayList<BibtexEntry> filter (){
        ArrayList<BibtexEntry> filtered = new ArrayList<BibtexEntry>();


        for(BibtexEntry bibEntry : E){
            if (bibEntry.type.equals(check))
                filtered.add(bibEntry);
        }

        return filtered;
    }
}
