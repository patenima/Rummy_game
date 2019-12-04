/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sheridancollege.project;

import java.util.LinkedList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nima
 */
public class DecksTest {
    
    public DecksTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of makeDeck method, of class Decks.
     */
    @Test
    public void testMakeDeckGood() {
        System.out.println("makeDeck");
        Decks instance = new Decks();
        LinkedList<Card> expResult = new LinkedList<>();
        LinkedList<Card> result = instance.makeDeck();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    @Test
    public void testMakeDeckBad() {
        System.out.println("makeDeck");
        Decks instance = new Decks();
        LinkedList<Card> expResult = new LinkedList<>();
        expResult.add(null);
        
        LinkedList<Card> result = null;
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    @Test
    public void testMakeDeckBoundary() {
        System.out.println("makeDeck");
        Decks instance = new Decks();
        LinkedList<Card> expResult = new LinkedList<>();
        
        LinkedList<Card> result = instance.makeDeck();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }



    /**
     * Test of shuffle method, of class Decks.
     */
     @Test
    public void testShuffleGood() {
        System.out.println("shuffle");
        LinkedList<Card> decksCard = new LinkedList<>();
        decksCard.add(null);
        Decks instance = new Decks();
        instance.shuffle(decksCard);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test
    public void testShuffleBad() {
        System.out.println("shuffle");
        LinkedList<Card> decksCard = null;
        Decks instance = null;
        instance.shuffle(decksCard);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test
    public void testShuffleBoundary() {
        System.out.println("shuffle");
        LinkedList<Card> decksCard = null;
        Decks instance = new Decks();
        instance.shuffle(decksCard);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of deal method, of class Decks.
     */
    @Test
    public void testDealGood() {
        System.out.println("deal");
        LinkedList<Card> decksCard = null;
        Decks instance = new Decks();
        Card expResult = null;
        Card result = instance.deal(decksCard);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test
    public void testDealBad() {
        System.out.println("deal");
        LinkedList<Card> decksCard = null;
        Decks instance = new Decks();
        Card expResult = null;
        Card result = null;
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test
    public void testDealBoundary() {
        System.out.println("deal");
        LinkedList<Card> decksCard = null;
        Decks instance = new Decks();
        Card expResult = null;
        Card result = instance.deal(decksCard);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
