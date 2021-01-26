/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop1;

import com.sun.javafx.scene.layout.region.Margins;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Stack;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author berkh
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Button SpellCheck;
    @FXML
    private TextField SearchButton;
    private Button Open;
    @FXML
    public TextArea InputText;
    @FXML
    private Button Search;
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private TextArea CorrectText;
    @FXML
    private Button nextSearch;

    @FXML
    private MenuItem fileOpen;
    @FXML
    private MenuItem saveFile;
    @FXML
    private Button fix;
    @FXML
    private Label IncorrectWCount;
    @FXML
    private Label FixedWCount;
    @FXML
    private AnchorPane ChangePane;
    @FXML
    private TextField changeWordInput;
    @FXML
    private RadioButton changeSelected;
    @FXML
    private RadioButton changeAll;
    @FXML
    private Button changeButton;
    @FXML
    private Button undo;

    FileChooser fileChooser = new FileChooser();
    private CommandStack commandStack;
    public int selectedIndex;

    /*
    SpellCheck butonuna tıklandığında editöre yazılı olan texti spellchecker
    metoduna sokar ve değiştirilen kelime varsa aşağıdaki metin kutusunda
    düzeltilmiş metin yazdırılır
     */
    @FXML
    private void handleSpellCheck(ActionEvent event) {
        TextEditor.text = InputText.getText();
        TextEditor.readWords();
        String correct = TextEditor.spellChecker();
        if (!correct.equals(TextEditor.text)) {
            CorrectText.setText(correct);
            fix.setDisable(false);

        } else {

            CorrectText.setText("");
            fix.setDisable(true);
        }
    }

    /*
    Girilen kelimeyi Editördeki metnin içinde aratır ve ilk bulunan sonucu
    işaretler. Bulunan diğer sonuçları seçmek için kullanılacak butonu da
    aktif eder.
     */
    @FXML
    private void handleSearchButton(ActionEvent event) {

        TextEditor.text = InputText.getText();
        TextEditor.readWords();
        String word = SearchButton.getText();
        TextEditor.findWord(word);
        ArrayList<Integer> indexes = TextEditor.foundWordIndexes;

        if (indexes.size() > 1) {

            nextSearch.setVisible(true);

        }
        if (indexes.size() > 0) {

            nextSearch.setDisable(false);
            ChangePane.setVisible(true);
            int k = TextEditor.wordIndexes.get(indexes.get(0));
            InputText.selectRange(k, k + word.length());
        } else {
            InputText.deselect();
            nextSearch.setDisable(true);
        }

    }

    /*
    Bulunan kelimelerden bir sonrakinin işaretlenmesine yarayan buton.
    Son kelimeden sonra başa döner.
     */
    @FXML
    private void handleNextSearch(ActionEvent event) {
        TextEditor.foundSelectedWord = (TextEditor.foundSelectedWord + 1) % TextEditor.foundWordIndexes.size();

        int k = TextEditor.wordIndexes.get(TextEditor.foundWordIndexes.get(TextEditor.foundSelectedWord));
        InputText.selectRange(k, k + SearchButton.getText().length());

    }

    /*
    Aranılan kelimenin verilen string ile değiştirilmesi metodu.
    Kullanıcı sadece seçili olan mı yoksa bütün bulunan kelimelerin mi
    değiştirileceğini seçer.
    Bu seçime göre program bulunan keliemelerin indexlerini kullanarak
    seçili kelimeleri istenen string ile değiştirir.
     */
    @FXML
    private void handleChangeButton(ActionEvent event) {
        int lenght = SearchButton.getText().length();
        if (changeSelected.isSelected()) {

            String change = TextEditor.changeString(InputText.getText(), TextEditor.wordIndexes.get(TextEditor.foundWordIndexes.get(TextEditor.foundSelectedWord)), changeWordInput.getText(), lenght);
            InputText.setText(change);
            TextEditor.text = InputText.getText();
            TextEditor.readWords();
        } else {

            for (int i = 0; i < TextEditor.foundWordIndexes.size(); i++) {
                TextEditor.text = InputText.getText();
                TextEditor.readWords();
                String change = TextEditor.changeString(InputText.getText(), TextEditor.wordIndexes.get(TextEditor.foundWordIndexes.get(i)), changeWordInput.getText(), lenght);
                InputText.setText(change);

            }

        }

        ChangePane.setVisible(false);

    }

    //bilgisayardan bir txt dosyası açılmasını sağlayan metod.
    @FXML
    private void handleOpen(ActionEvent event) throws NullPointerException {
        Window stage = Search.getScene().getWindow();
        fileChooser.setTitle("Open a Text File");

        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text File", "*.txt"));

        try {
            File file = fileChooser.showOpenDialog(stage);
            fileChooser.setInitialDirectory(file.getParentFile());
            Scanner scan = new Scanner(file);
            String newFile = "";
            while (scan.hasNextLine()) {
                newFile += scan.nextLine();

            }

            InputText.setText(newFile);

        } catch (Exception e) {
        }

    }

    //Editördeki metni bir txt dosyası olarak kaydetmeye yarayan metod.
    @FXML
    private void handleSave(ActionEvent event) throws NullPointerException {
        Window stage = Search.getScene().getWindow();

        fileChooser.setTitle("Save Edited Text");

        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text File", "*.txt"));

        try {

            File file = fileChooser.showSaveDialog(stage);
            fileChooser.setInitialDirectory(file.getParentFile());

            FileWriter fw = new FileWriter(file);
            fw.write(CorrectText.getText());

            fw.close();
        } catch (Exception e) {

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        TextEditor.InitializeFunctions();
        Dictionary.readDict();
        fileChooser.setInitialDirectory(new File("C:\\"));
        selectedIndex = 0;
        nextSearch.setDisable(true);
        TextEditor.foundWordIndexes = new ArrayList<Integer>();
        TextEditor.words = new ArrayList<String>();
        TextEditor.wordIndexes = new ArrayList<Integer>();
        commandStack = new CommandStack(10);
    }

    /*
    Aşağıdaki metin kutusundaki düzeltilmiş metni yukarıdaki metin kutusuna 
    taşıyan metod.
     */
    @FXML
    private void fixInputText(ActionEvent event) {

        InputText.setText(CorrectText.getText());
        CorrectText.setText("");
        TextEditor.text = InputText.getText();
        TextEditor.readWords();
        fix.setDisable(true);

    }

    @FXML
    private void whileTypingSearch(KeyEvent event) {
        TextEditor.foundSelectedWord = 0;
        TextEditor.foundWordIndexes.clear();
        nextSearch.setDisable(true);

        InputText.deselect();
    }

    KeyCode[] ignoreKeys = {KeyCode.UP, KeyCode.DOWN, KeyCode.RIGHT, KeyCode.LEFT, KeyCode.SHIFT, KeyCode.CONTROL, KeyCode.ALT, KeyCode.TAB};

    //Text alanına her key basışta yapılan işleme göre command stack'e
    //command objesi pushlama.
    @FXML
    private void onKeyPressed(KeyEvent event) {
        try {
            int start = InputText.getSelection().getStart();
            int end = InputText.getSelection().getEnd();
            if (event.getCode() == KeyCode.BACK_SPACE) {//Backspace tuşuna basıldıysa.

                if (start != end) {//1 Karakterlik silme işlemi uygulandıysa
                    commandStack.Push(new Command("DELETE", InputText.getSelectedText(), start));
                } else {//1 karakterden fazla bir string seçilip silindiyse
                    commandStack.Push(new Command("DELETE", InputText.getText().substring(end - 1, end), start - 1));
                }

                //System.out.println("İŞLEM TİPİ: " + commandStack.Peek().commandType + " -- İşlem: " + commandStack.Peek().command + " " + commandStack.Peek().index);
            } else if (event.getCode() == KeyCode.DELETE) {//DELETE tuşuna basıldıysa
                if (start != end) {//1 Karakterlik silme işlemi uygulandıysa
                    commandStack.Push(new Command("DELETE", InputText.getSelectedText(), start));
                } else {//1 karakterden fazla bir string seçilip silindiyse
                    commandStack.Push(new Command("DELETE", InputText.getText().substring(end, end + 1), start - 1));
                }

                //System.out.println("İŞLEM TİPİ: " + commandStack.Peek().commandType + " -- İşlem: " + commandStack.Peek().command + " " + commandStack.Peek().index);
            } else if (event.getCode() != ignoreKeys[0]
                    && event.getCode() != ignoreKeys[1]
                    && event.getCode() != ignoreKeys[2]
                    && event.getCode() != ignoreKeys[3]
                    && event.getCode() != ignoreKeys[4]
                    && event.getCode() != ignoreKeys[5]
                    && event.getCode() != ignoreKeys[6]
                    && event.getCode() != ignoreKeys[7]) {
                /*
                Backspace , DELETE , TAB , CTRL , ALT ,SHIFT veya
                ok tuşlarından biri basılmadıysa. Basılan tuşun bir karakter
                olduğunu varsayarak Bu karakterin bilgilerini içeren bir 
                Command objesi oluşturulur ve Stack'e eklenir.
                
                Burada belirtilen tuşlardan farklı olup bir karakter tuşu olmayan
                bir tuşa basıldığında program hata verebilir. Bunların yoksayılması
                özelliği eklenmemiştir. Sadece karakter tuşlarına basılacağı
                varsayılır.
                 */
                commandStack.Push(new Command("TYPE", event.getText(), start));

                //System.out.println("İŞLEM TİPİ: " + commandStack.Peek().commandType + " -- İşlem: " + commandStack.Peek().command + " " + commandStack.Peek().index);
            }
        } catch (java.lang.StringIndexOutOfBoundsException e) {
        }

    }

    @FXML
    private void handleSelectedCheck(ActionEvent event) {

        changeAll.setSelected(false);
    }

    @FXML
    private void handleAllCheck(ActionEvent event) {
        changeSelected.setSelected(false);
    }

    @FXML
    private void handleUndo(ActionEvent event) {
        try {
            /*
            Geri al tuşuna basıldığında commandStack'ten pop edilen command'in
            içeriği kontrol edilir ve bu içeriğe göre gerekli işlem yapılır.
            
            Örneğin "DELETE" işlemine sahip içeriği "abc" olan ve indexi 3 olan
            bir komut undo edildiğinde. Program o sırada metin kutusunun 3. 
            indexine "abc" metnini yazacaktır.
            
            "TYPE" işlemine sahip indexi 4 olan komut undo edildiğinde. Program
            4. indexteki harfi silecektir. Bu command tipinde command'in içeriği
            önemli değildir. Sadece silinecek harfi gösterir ama işlemde kullanılmaz.
             */
            Command command = commandStack.Pop();
            if ("TYPE".equals(command.commandType)) {
                String txt = InputText.getText();
                InputText.setText(txt.substring(0, command.index) + txt.substring(command.index + 1));
            } else if ("DELETE".equals(command.commandType)) {
                InputText.setText(TextEditor.addString(InputText.getText(), command.index, command.command));

            }
        } catch (NullPointerException e) {
            System.out.println("nuulpoi");
        }
    }

}
