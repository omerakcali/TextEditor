/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop1;

import java.util.Stack;

/**
 *
 * @author PC
 */
public class CommandStack {

    public Stack<Command> commandStack;
    int size;

    public CommandStack(int size) {
        this.size = size;
        commandStack = new Stack<Command>();
    }

    //Komutları belirlenen büyüklükteki yığıta atar. Yığıt dolduğunda en geçmişteki
    //komutlardan silmeye başlar.
    public void Push(Command c) {
        if (commandStack.size() >= size) {
            commandStack.remove(0);
        }
        commandStack.push(c);

    }

    //Yığıta son eklenen komut objesini yığıttan çıkarır ve döndürür.
    public Command Pop() {
        if (commandStack.size() > 0) {
            return commandStack.pop();
        } else {
            return null;
        }

    }

    //Yığıta son eklenen komut objesini döndürür.
    public Command Peek() {
        if (commandStack.size() > 0) {
            return commandStack.peek();
        } else {
            return null;
        }
    }

}
