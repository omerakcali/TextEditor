/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
    public void setUp() {

        dr = new Dictionary();
        dr.readDict();

    }

    @Test
    public void DictionaryWordsTest() {

        assertNotNull("Words array could not created.", dr.words);
    }

    @Test
    public void DictionarySearchTest() {
        boolean booltrue = dr.Search("aalii");
        boolean boolfalse = dr.Search("jewropupwrjweorp");
        assertFalse(boolfalse);
        assertTrue(booltrue);
    }

    @Test
    public void TestTextEditorWords() {
        TextEditor.text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore.";
        TextEditor.readWords();
        String[] arrayString = {"Lorem", "ipsum", "dolor", "sit", "amet", "consectetur", "adipiscing", "elit", "sed", "do", "eiusmod", "tempor", "incididunt", "ut", "labore"};
        assertArrayEquals(arrayString, TextEditor.words);
    }

    @Test
    public void TestTextEditorSpellCheck() {
        TextEditor.text = "fullback, covers. umbrella: full-txet ıyoyıoyıu";
        TextEditor.readWords();
        String fixedText = TextEditor.spellChecker();
        String expectedText = "fullback, covers. umbrella: full-text ıyoyıoyıu";
        assertEquals(expectedText, fixedText);
    }

    @Test
    public void TestTextEditorConvertIntegers() {
        int[] expected = {1, 2, 3, 4, 5};
        ArrayList<Integer> alist = new ArrayList<Integer>();
        for (int i = 0; i < 5; i++) {
            alist.add(i + 1);

        }
        assertArrayEquals(expected, TextEditor.convertIntegers(alist));
    }

    @Test
    public void TestTextEditorChangeString() {
        String actual = "changeTHISchange";
        String expected = "changeCHANGEchange";
        assertEquals(expected, TextEditor.changeString(actual, 6, "CHANGE", 4));
    }

    @Test
    public void TestTextEditorFindWord() {
        TextEditor.text = "Words words aaaa woRdS aa bb";
        TextEditor.readWords();
        int[] expected = {0, 1, 3};
        TextEditor.findWord("words");
        assertArrayEquals(expected, TextEditor.foundWordIndexes);
    }

}
