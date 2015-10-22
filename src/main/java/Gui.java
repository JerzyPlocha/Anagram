import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui extends JFrame implements ActionListener {

    private DefaultListModel<String> listModel = new DefaultListModel<String>();
    private Anagram anagram;
    private TextField textField;

    public Gui() {

        setLayout(new MigLayout("fill", "[200:500:]", "25[:30:30]25[200:300:]"));

        textField = new TextField();
        textField.addActionListener(this);

        JList<String> jList = new JList<String>(listModel);
        jList.setLayoutOrientation(JList.VERTICAL);

        JScrollPane jScrollPane = new JScrollPane(jList);

        textField.setMinimumSize(new Dimension(200, 20));

        add(textField, "gap 30 30, grow, wrap");
        add(jScrollPane, "grow");

        setLocation(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {

        refreshListModel();

        String query = textField.getText();

        if(query.equals("") || query.equals("*"))
            listAnagrams();
        else if(query.contains("."))
            template(query);
        else
            anagramsOf(query);
    }

    private void listAnagrams() {
        refreshListModel();
        for(WordList wordList : anagram.printAnagrams()){
            listModel.add(listModel.size(), wordList.toString());
        }
    }

    private void template(String query) {
        refreshListModel();
        for(String string : anagram.listMatching(query)){
            listModel.add(listModel.size(), string);
        }
    }

    private void anagramsOf(String query) {
        WordList wordList = (WordList) anagram.lookUp(query);
        if(wordList != null){
            for(String string : wordList){
                listModel.add(listModel.size(), string);
            }
        } else {
            refreshListModel();
            listModel.add(0, " --- (brak anagramów) --- ");
        }
    }

    public void refreshListModel() {
        listModel.removeAllElements();
    }

    public Anagram getAnagram() {
        return anagram;
    }

    public void setAnagram(Anagram anagram) {
        this.anagram = anagram;
    }
}
