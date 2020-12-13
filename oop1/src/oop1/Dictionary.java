/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package oop1;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author berkh
 */
public class Dictionary {
    
  
    String[] words;
    
    public void readDict(){
        try {
        File wordFile = new File("words.txt");
        Scanner rd = new Scanner(wordFile);
        
        ArrayList<String> wordList = new ArrayList<String>();
        
        while(rd.hasNextLine()){
            String tmpWord = rd.nextLine();
            wordList.add(tmpWord);
            
        }
        
        words= wordList.toArray(new String[wordList.size()]);
        
     
        
        } catch (FileNotFoundException e) {
            System.out.println("Dictionary not found.");
            
        }
        
        
    }
  
    
    
}
