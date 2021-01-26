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
public class Command {

    public String commandType;
    public String command;
    public int index;

    public Command(String type, String str, int index) {
        this.commandType = type;
        this.command = str;
        this.index = index;
    }

}
