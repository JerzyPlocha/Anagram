import java.util.ArrayList;
import java.util.Comparator;

public class WordList extends ArrayList<String> implements Comparable<WordList> {

    public WordList() {
    }

    public WordList(String string) {
        this.add(string);
    }

    @Override
    public boolean add(String string) {
        if(!this.contains(string)){
            super.add(string);
            this.sort(new Comparator<String>() {
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            });
            return true;
        }
        return false;
    }

    public WordList addAndReturn(String string) {
        this.add(string);
        return this;
    }

    public int compareTo(WordList o) {
        return o.size() - this.size();
    }
}
