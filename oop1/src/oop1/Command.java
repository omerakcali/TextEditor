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
public interface Command {

    void Execute(FXMLDocumentController fxml);
    void Undo(FXMLDocumentController fxml);
    }


