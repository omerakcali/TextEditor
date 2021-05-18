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
public class ErrorFunctionFactory {

    //Girilen fonksiyon adına göre fonksiyon objesi yaratma
    public SpellErrorFunctions createFunction(String FunctionName) {
        if (FunctionName.equals("SingleTransposition")) {
            return new SingleTransposition();
        } else {
            /*
            Şu anda sadece Single Transposition fonksiyonu implement edilmiş
            durumda. Fakat Yeni fonksiyonlar eklendiğinde buraya eklenecek 2-3 
            satır kod ile yeni fonksiyonların objelerinin de oluşturulması
            sağlanabilecektir.
            */
            System.err.println("FunctionNot implemented");
        }

        return null; 
    }
}
