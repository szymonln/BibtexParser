import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Pattern;

public class AuthorFilter implements Filter {

    String check;
    ArrayList<BibtexEntry> E;

    AuthorFilter(String check, ArrayList<BibtexEntry> E){
        this.check = check;
        this.E = E;
    }

    public ArrayList<BibtexEntry> filter (){
        ArrayList<BibtexEntry> filtered = new ArrayList<BibtexEntry>();


        for(BibtexEntry bibEntry : E){
            for(Map.Entry<String, String> mapEntry : bibEntry.Rekordy.entrySet()) {
                String key = mapEntry.getKey();
                String value = mapEntry.getValue();
                boolean match=false;
                if(value!=null) {
                    String result[] = value.split(" ");
                    for (int i = 0; i < result.length; i++) {
                        if(result[i].equals(check))
                            match=true;
                    }
                }
                if (key != null && value != null) {
                    if (key.equals("author") && match) {
                        filtered.add(bibEntry);
                    }
                }
            }
        }
        return filtered;
    }

}

