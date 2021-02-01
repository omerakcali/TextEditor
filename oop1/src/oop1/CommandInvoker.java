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
public class CommandInvoker {

    CommandStack stack;

    public CommandInvoker(int size) {
        stack = new CommandStack(size);
    }

    void DoCommand(FXMLDocumentController fxml,Command c){
        c.Execute(fxml);
        stack.Push(c);
    }
    
    void UndoCommand(FXMLDocumentController fxml){
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
        stack.Pop().Undo(fxml);
    }
    
}
