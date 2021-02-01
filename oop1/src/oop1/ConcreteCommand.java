/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop1;

/**
 *
 * @author PC
 */
public class ConcreteCommand implements Command {
     /*
    CTRL-C CTRL-X CTRL-V gibi farklı kısayolları desteklememektedir.
    Sadece klavyeyle girilen karakterlerin ve Beckspace veya DELETE ile silinen
    metinlerin kaydını tutar. farklı tuşlara ve tuş kombinasyonlarına basılması
    Hataya neden olabilir.
    */
    public String commandType; //Komutun tipi "DELETE, "TYPE" gibi
    public String command; //Komutta yazılan veya silinen şey
    public int index; //Komudun uygulandığı index

    public ConcreteCommand(String type, String str, int index) {
        this.commandType = type;
        this.command = str;
        this.index = index;
}

    @Override
    public void Execute(FXMLDocumentController fxml) {
        TextEditor.text=fxml.InputText.getText();
        TextEditor.readWords();
    }

    @Override
    public void Undo(FXMLDocumentController fxml) {
       if ("TYPE".equals(commandType)) {
                String txt = fxml.InputText.getText();
                fxml.InputText.setText(txt.substring(0, index) + txt.substring(index + 1));
            } else if ("DELETE".equals(commandType)) {
                fxml.InputText.setText(TextEditor.addString(fxml.InputText.getText(), index,command));
    }
    }
}
