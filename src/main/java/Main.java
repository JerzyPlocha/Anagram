import java.io.File;

public class Main {

    public static void main(String[] args) {

        Gui gui = new Gui();

        Anagram anagram = new Anagram(new File("/Users/jerzyplocha/IdeaProjects/UTP/Z4/src/main/resources/english_words_LIN.txt"));
        gui.setAnagram(anagram);
    }
}