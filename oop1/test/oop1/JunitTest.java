/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop1;
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
    
   public DictionaryReader dr;
   @Before
   public void setUp(){
   dr= new DictionaryReader();
    
   
   
   }
   @Test
   public void cuguba(){
       assertNull("cugu", dr);
   }
}
