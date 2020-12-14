/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop1;
import java.io.File;
import java.io.FileNotFoundException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author berkh
 */
public class JunitTest {
    
   public Dictionary dr;
   @Before
   public void setUp(){
      
       dr =new Dictionary();
       dr.readDict();
   }
   @Test
   public void DictionaryWordsTest(){
       
       assertNotNull("Words array could not created.", dr.words);
   }
   
   @Test
   public void DictionarySearchTest(){
       boolean booltrue = dr.Search("aalii");
       boolean boolfalse = dr.Search("jewropupwrjweorp");
       assertFalse(boolfalse);
       assertTrue(booltrue);
   }
   
   
}
