/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop1;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
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
    private void handleSpellCheck(ActionEvent event) {
       
       TextEditor.text= InputText.getText();
       TextEditor.readWords();
     
       CorrectText.setText(TextEditor.spellChecker());
        
    }
    
    @FXML
    private void handleSearchButton(ActionEvent event) {
        
    }
    
    @FXML
    private void handleOpen(ActionEvent event) {
       
    }
    
    @FXML
    private void handleSave(ActionEvent event) {
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
