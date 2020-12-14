/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
   @FXML
    private Button Open;
   @FXML
    private Button Save;
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
    
    public int selectedIndex;
    
   ArrayList<Integer> indexes;
    
    
    
    
    @FXML
    private void handleSpellCheck(ActionEvent event) {
       
       TextEditor.text= InputText.getText();
       
       TextEditor.readWords();
       String correct =TextEditor.spellChecker();
       CorrectText.setText(correct);
       
        
    }
    
    @FXML
    private void handleSearchButton(ActionEvent event) {
        TextEditor.text= InputText.getText();
       TextEditor.readWords();
       indexes=new ArrayList<Integer>();
        String word = SearchButton.getText();
        for (int i = 0; i < TextEditor.words.length; i++) {
            if(TextEditor.words[i].equals(word)){
                indexes.add(i);
            }
        }
        if(indexes.size()>0){
            int j = indexes.get(0);
            int k =TextEditor.wordIndexes[j];
            System.out.println(k);
            InputText.selectRange(k,k+ word.length());
        }else{
            InputText.deselect();
        }
        
    }
    
    @FXML
    private void handleOpen(ActionEvent event) {
       Window stage = Open.getScene().getWindow();
       fileChooser.setTitle("Open a Text File");
       
       fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text File","*.txt"));
       
        try {
            File file = fileChooser.showOpenDialog(stage);
            fileChooser.setInitialDirectory(file.getParentFile());
            Scanner scan = new Scanner(file);                     
            String newFile = "";
            while (scan.hasNextLine()) {
                newFile+=scan.nextLine();
                
            }
            
            InputText.setText(newFile);
            
            
        } catch (Exception e) {
        }
       
    }
    
    @FXML
    private void handleSave(ActionEvent event) {
        Window stage = Open.getScene().getWindow();
        
        fileChooser.setTitle("Save Edited Text");
       
       fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text File","*.txt"));
       
        try {
          
            
            File file = fileChooser.showSaveDialog(stage);
            fileChooser.setInitialDirectory(file.getParentFile());
           
            FileWriter fw = new FileWriter(file);
            fw.write(CorrectText.getText());
           
            fw.close();
        } catch (IOException e) {
            
           
        }
    
    }
    
    
    
    FileChooser fileChooser = new FileChooser();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Dictionary.readDict();
        fileChooser.setInitialDirectory(new File("C:\\"));
        selectedIndex=0;
        indexes=new ArrayList<Integer>();
    }    

    @FXML
    private void handleNextSearch(ActionEvent event) {
        selectedIndex= (selectedIndex+1)%indexes.size();
        int j = indexes.get(selectedIndex);
            int k =TextEditor.wordIndexes[j];
        InputText.selectRange(k,k+ SearchButton.getText().length());
    }
    
}
