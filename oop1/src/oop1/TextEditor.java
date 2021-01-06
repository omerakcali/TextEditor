/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop1;

import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class TextEditor {

    public static String text;
    public static String[] words;
    public static int[] wordIndexes;
    public static int[] foundWordIndexes;
    public static int foundSelectedWord;

    /* 
    O sırada Editöre yazılmış olan text'in içerisindeki kelimeleri words 
    array'ine aktaran method.
    Bir çok işlem gerçekleştirilmeden önce çağrılıp bellekteki kelimeleri 
    güncellemek için kullanılır.
    Bu metod aynı zamanda bütün kelimelerin kaçıncı indexlerde başladığını 
    tutan wordIndexes array'ini de oluşturur.
     */
    public static void readWords() {
        ArrayList<String> list = new ArrayList<String>();
        ArrayList<Integer> indexList = new ArrayList<>();
        int i = 0, j = 0;
        String word = "";
        /*
        Text içerisinde {',' , ' ' , '.' , ':' , ';' , '!' , '-' , '?'} ile
        ayrılmış kelimeleri seçip words arrayini oluşturma döngüsü.
         */
        while (i < text.length()) {

            while (text.charAt(i) == ',' || text.charAt(i) == ' '
                    || text.charAt(i) == '.' || text.charAt(i) == ':'
                    || text.charAt(i) == ';' || text.charAt(i) == '!'
                    || text.charAt(i) == '-' || text.charAt(i) == '?') {
                i++;
            }
            j = i;
            while (j < text.length()) {
                if (text.charAt(j) == ',' || text.charAt(j) == ' '
                        || text.charAt(j) == '.' || text.charAt(j) == ':'
                        || text.charAt(j) == ';' || text.charAt(j) == '!'
                        || text.charAt(j) == '-' || text.charAt(j) == '?') {

                    break;
                }
                j++;
            }
            word = text.substring(i, j);
            indexList.add(i);
            list.add(word);
            i = j + 1;

        }
        words = list.toArray(new String[list.size()]);
        wordIndexes = convertIntegers(indexList);
    }

    /*
    Kelimeler array'indeki kelimeleri Dictionary Class'ındaki 
    arama metodunda tek tek aratır, kelime bulunduysa olduğu gibi geçirir
    bulunmadıysa kelimenin single transposition'lu hallerini de tek tek aratır.
    Single transposition yapılmış haller sözlükte bulunursa düzeltilmiş kelime 
    yeni oluşturulacak metine eklenir. Bulunamadıysa sözcük hatalıdır ve olduğu
    gibi hatalı haliyle yeni metine eklenir.
    Metinin son hali return edilir
     */
    public static String spellChecker() {
        int spellingErrorCount = 0;
        int fixCount = 0;
        String newText = text;
        for (int i = 0; i < words.length; i++) {
            if (!Dictionary.Search(words[i])) {
                spellingErrorCount++;
                for (int j = 0; j < words[i].length() - 1; j++) {
                    //Single Transposition kombinasyonlarının uygulaması
                    String transpose = words[i].charAt(j + 1) + ""
                            + words[i].charAt(j);
                    String temp = changeString(words[i], j, transpose, 2);


                    /*Herhangi bir single transposition ile oluşan kelime 
                    sözlükte bulunursa düzeltilmiş hali yeni textte yerine 
                    eklenir.*/
                    if (Dictionary.Search(temp)) {
                        fixCount++;
                        newText = changeString(newText, wordIndexes[i], temp, words[i].length());
                    }
                }
            }
        }
        return newText;
    }

    /*
    Parametre olarak girilen aranacak kelimeyi bellekteki words array'inde 
    aratır.
    eğer kelime bulunduysa bulundukları yerlerin indexleri
    foundWordIndexes array'inde tutulur.
    Bulunmadıysa bu array 0 elemanlı olacaktır.
     */
    public static void findWord(String word) {
        int found = 0;
        foundSelectedWord = 0;
        for (String word1 : words) {
            if (word1.equalsIgnoreCase(word)) {
                found++;
            }
        }
        foundWordIndexes = new int[found];
        found = 0;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equalsIgnoreCase(word)) {
                foundWordIndexes[found] = i; //Kelimenin indeksini kaydetme
                found++;
            }
        }
    }

    //verilen stringin içerisindeki bir parçayı başka bir string parçasıyla değiştirme metodu
    public static String changeString(String text, int index, String word, int length) {
        return text.substring(0, index) + word + text.substring(index + length);
    }

    //int tipli arraylisti int tipli array'e çeviren metod.
    public static int[] convertIntegers(ArrayList<Integer> integers) {
        int[] ret = new int[integers.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = integers.get(i);
        }
        return ret;
    }

}
