/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop1;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author PC
 */
public class TextEditor {

    public static ArrayList<SpellErrorFunctions> spellErrorFunctions = new ArrayList<SpellErrorFunctions>();

    public static String text;
    /* words, wordsindexes ve foundwordindexes arrayleri, iterator kullanımı
    sağlanabilmesi için arrayliste çevrildi.
     */
    public static ArrayList<String> words;
    public static ArrayList<Integer> wordIndexes;
    public static ArrayList<Integer> foundWordIndexes;
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
        words.clear();
        wordIndexes.clear();
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
            wordIndexes.add(i);
            words.add(word);
            i = j + 1;

        }
    }

    /*
    Kelimeler array'indeki kelimeleri Dictionary Class'ındaki 
    arama metodunda tek tek aratır, kelime bulunduysa olduğu gibi geçirir
    bulunmadıysa kelimeyi tek tek yazım hatası düzeltme algoritmalarının olduğu
    ArrayListteki fonksiyonlara sokar.
    
    Kelime düzeltilebilmiş ise fonksiyondan farklı bir halde çıkar.
    Düzeltilememiş ise olduğu gibi çıkar. 
    
    Kelimenin fonksiyondan çıkmış hali
    yeni oluşturulacak metine eklenir. Bulunamadıysa sözcük hatalıdır ve olduğu
    gibi hatalı haliyle yeni metine eklenir.
    
    Metinin son hali return edilir
     */
    public static String spellChecker() {
        int spellingErrorCount = 0;
        int fixCount = 0;
        String newText = text;
        Iterator<String> wordsIt = words.iterator();
        int index = 0;
        while (wordsIt.hasNext()) {
            String word = wordsIt.next();
            if (!Dictionary.Search(word)) {
                spellingErrorCount++;
                Iterator<SpellErrorFunctions> funcIt = spellErrorFunctions.iterator(); //iterator design pattern
                while (funcIt.hasNext()) {
                    String temp = funcIt.next().apply(word);
                    if (temp != null) {
                        fixCount++;
                        newText = changeString(newText, wordIndexes.get(index), temp, word.length());
                        break;
                    }
                }

            }
            index++;
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
        foundWordIndexes = new ArrayList<Integer>();
        foundSelectedWord = 0;
        Iterator<String> wordsIt = words.iterator();
        int i = 0;
        while (wordsIt.hasNext()) {
            String word1 = wordsIt.next();
            if (word1.equalsIgnoreCase(word)) {
                foundWordIndexes.add(i);
                found++;
            }
            i++;
        }

        /*found = 0;
        for (int i = 0; i < words.length; i++) {
            if (word.equalsIgnoreCase(word)) {
                foundWordIndexes[found] = i; //Kelimenin indeksini kaydetme
                found++;
            }
        }*/
    }

    //Yazım Hatası Düzeltme algoritmalarının bulunduğu ArrayList'in doldurulması
    public static void InitializeFunctions() {
        ErrorFunctionFactory factory = new ErrorFunctionFactory();//Factory Design Pattern
        spellErrorFunctions.add(factory.createFunction("SingleTransposition"));

    }

    //verilen stringin içerisindeki bir parçayı başka bir string parçasıyla değiştirme metodu
    public static String changeString(String text, int index, String word, int length) {

        return text.substring(0, index) + word + text.substring(index + length);
    }

    //belirli bir stringde verilen indexe başka bir string insert etme metodu
    public static String addString(String text, int index, String word) {
        System.out.println(text + " " + index + " " + word);
        if (index != text.length()) {
            return text.substring(0, index) + word + text.substring(index);
        } else {
            return text + word;
            
        }
    }
}
