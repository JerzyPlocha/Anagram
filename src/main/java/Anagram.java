import java.io.*;
import java.util.*;

public class Anagram {


    ArrayList<String> list;
    Map<String, WordList> map;
    File file;

    public Map<String, WordList> getMap() { return map; }

    public void setMap(Map<String, WordList> map) { this.map = map; }

    public void setList(WordList list) { this.list = list; }

    public ArrayList<String> getList() { return list; }

    public Anagram(File file) {
        this.file = file;
        list = new WordList();
        map = new TreeMap<String, WordList>(new Comparator<String>() {
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        }
        );
        readList(file);
    }


    // zwraca posortowaną wersję

    public String sortedWord(String string) {
        char[] chars = string.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }


    // dodaje do mapy słowo (do kontenera WordList) pod klucz posortowanej wersji (albo jakiś sortedWord)

    public void addToMap(String string) {
        String hash = sortedWord(string);
        if (!map.containsKey(hash))
            map.put(hash, new WordList(string));
        else
            map.put(hash, map.get(hash).addAndReturn(string));
    }


    // na pytanie mapy o słowo, odpowiada listą anagramów (wartości mapy - listy)

    public List<String> lookUp(String query) {
        String queryKey = sortedWord(query);
        return map.get(queryKey);
    }

    // wydruk powinnien uwzględniać tylko przypadki, gdy liczba anagramów większa niż 2
    public ArrayList<WordList> printAnagrams() {

        ArrayList<WordList> result = new ArrayList<WordList>();

        for (Map.Entry<String, WordList> entry : map.entrySet()) {
            if(entry.getValue().size() > 2){
                result.add(entry.getValue());
            }
        }

        result.sort(new Comparator<WordList>() {
            public int compare(WordList o1, WordList o2) {
                return o1.compareTo(o2);
            }
        });

        return result;

    }

    public void readList(File file) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"));
            String line;
            try {
                while ((line = br.readLine()) != null) {
                    addToMap(line);
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (UnsupportedEncodingException e) {

        }
    }

    public ArrayList<String> listMatching(String query){
        ArrayList<String> result = new ArrayList<String>();
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"));
            String line;
            try {
                while ((line = br.readLine()) != null) {
                    if(line.matches(query)){
                        result.add(line);
                    }
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (UnsupportedEncodingException e) {

        }
        return result;
    }

}
