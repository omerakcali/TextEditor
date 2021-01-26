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
public class SingleTransposition implements SpellErrorFunctions {

    @Override
    public String apply(String word) {

        for (int j = 0; j < word.length() - 1; j++) {
            //Single Transposition kombinasyonlarının uygulaması
            String transpose = word.charAt(j + 1) + ""
                    + word.charAt(j);
            String temp = TextEditor.changeString(word, j, transpose, 2);
            /*Herhangi bir single transposition ile oluşan kelime 
                    sözlükte bulunursa düzeltilmiş hali yeni textte yerine 
                    eklenir.*/
            if (Dictionary.Search(temp)) {
                return temp;
            }
        }

        return word;
    }
}
