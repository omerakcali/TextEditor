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

    /*
    CTRL-C CTRL-X CTRL-V gibi farklı kısayolları desteklememektedir.
    Sadece klavyeyle girilen karakterlerin ve Beckspace veya DELETE ile silinen
    metinlerin kaydını tutar. farklı tuşlara ve tuş kombinasyonlarına basılması
    Hataya neden olabilir.
    */
    public String commandType; //Komutun tipi "DELETE, "TYPE" gibi
    public String command; //Komutta yazılan veya silinen şey
    public int index; //Komudun uygulandığı index

    public Command(String type, String str, int index) {
        this.commandType = type;
        this.command = str;
        this.index = index;
    }

}
