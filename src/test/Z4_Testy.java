import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class Z4_Testy {

    Anagram anagram;
    WordList listOfDifferentWords;
    ArrayList<String> findAnagrams;

    @org.junit.Before
    public void setUp() throws Exception {
        anagram = new Anagram(new File("/Users/jerzyplocha/IdeaProjects/UTP/Z4/src/main/resources/english_words_LIN.txt"));
        listOfDifferentWords = new WordList();
        listOfDifferentWords.add("crates");
        listOfDifferentWords.add("carets");
        listOfDifferentWords.add("recast");
        listOfDifferentWords.add("traces");
        listOfDifferentWords.add("cartes");
        listOfDifferentWords.add("caters");
        listOfDifferentWords.add("stop");
        listOfDifferentWords.add("tops");
        listOfDifferentWords.add("post");
        listOfDifferentWords.add("spot");
        listOfDifferentWords.add("seat");
        listOfDifferentWords.add("eats");
        listOfDifferentWords.add("teas");
        listOfDifferentWords.add("sate");
        listOfDifferentWords.add("east");
        anagram.setList(listOfDifferentWords);
    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    // Testy funkcji do pierwszego podpunktu zadania ( "templ.te" )
    @Test
    public void testListMatchingWords(){
        findAnagrams = anagram.listMatching("s..t");
        assertTrue(findAnagrams.contains("seat"));
        assertTrue(findAnagrams.contains("spot"));
    }

    // Testy funkcji anagramów
    @Test
    public void testHash(){
        String cat = anagram.sortedWord("cat");
        String act = anagram.sortedWord("act");
        assertTrue(cat.equals(act));
    }

    @Test
    public void lookUp(){
        anagram.addToMap("crates");
        anagram.addToMap("carets");
        anagram.addToMap("traces");
        assertTrue(anagram.lookUp("recast").contains("crates"));
    }
}