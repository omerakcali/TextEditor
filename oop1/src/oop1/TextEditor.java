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

    public static void readWords() {
        ArrayList<String> list = new ArrayList<String>();
        ArrayList<Integer> indexList = new ArrayList<>();
        int i = 0, j = 0;
        String word = "";
        while (i < text.length()) {

            while (text.charAt(i) == ',' || text.charAt(i) == ' ' || text.charAt(i) == '.' || text.charAt(i) == ':' || text.charAt(i) == ';' || text.charAt(i) == '!' || text.charAt(i) == '-' || text.charAt(i) == '?') {
                i++;
            }
            j = i;
            while (j < text.length()) {
                if (text.charAt(j) == ',' || text.charAt(j) == ' ' || text.charAt(j) == '.' || text.charAt(j) == ':' || text.charAt(j) == ';' || text.charAt(j) == '!' || text.charAt(j) == '-' || text.charAt(j) == '?') {

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

    //spellchecker ile TextEditor.text değiştirildikten sonra TextEditor.readWords() bir kere çalıştırılmalı.
    //Bul ve Değiştiri yazarken bu metodun içindeki insertleme kodlarını alıp ayrı bir metod aç ömer abi.59. ve 64. satır
    public static String spellChecker() {
        int spellingErrorCount = 0;
        int fixCount = 0;
        String newText = text.toString();
        for (int i = 0; i < words.length; i++) {
            if (!Dictionary.Search(words[i])) {
                spellingErrorCount++;
                for (int j = 0; j < words[i].length() - 1; j++) {
                    //Single Transposition kombinasyonlarının uygulaması
                    String transpose = words[i].charAt(j + 1) + "" + words[i].charAt(j);
                    String temp = changeString(words[i], j, transpose, 2);
                    //String temp = words[i].substring(0, j) + words[i].charAt(j + 1) + words[i].charAt(j) + words[i].substring(j + 2);

                    //Herhangi bir single transposition ile oluşan kelime sözlükte bulunursa düzeltilmiş hali yeni textte yerine eklenir.
                    if (Dictionary.Search(temp)) {
                        fixCount++;
                        //newText = newText.substring(0, wordIndexes[i]) + temp + newText.substring(wordIndexes[i] + words[i].length());
                        newText = changeString(newText, wordIndexes[i], temp, words[i].length());
                    }
                }
            }
        }
        return newText;
    }

    public static void findWord(String word) {
        int found = 0;
        foundSelectedWord = 0;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equalsIgnoreCase(word)) {
                found++;
            }
        }
        foundWordIndexes = new int[found];
        found = 0;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equalsIgnoreCase(word)) {
                foundWordIndexes[found] = i;
                found++;
            }
        }
    }

    public static String changeString(String text, int index, String word, int length) {
        return text.substring(0, index) + word + text.substring(index + length);
    }

    public static int[] convertIntegers(ArrayList<Integer> integers) {
        int[] ret = new int[integers.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = integers.get(i).intValue();
        }
        return ret;
    }

}
