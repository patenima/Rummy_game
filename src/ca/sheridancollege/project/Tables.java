/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sheridancollege.project;

/**
 *
 * @author Nima
 */
import java.util.ArrayList;


public class Tables
{
// implements Comparable<Cards>{
    private static final ArrayList<ArrayList<Card>> tableCards = new ArrayList<ArrayList<Card>>();

    //see (get) whatever is on the table
    public static ArrayList<ArrayList<Card>> getTableCards() 
    {
        return tableCards;
    }

    //add to the Array if you add a run
    public static void newMeld(ArrayList<Card> meld)
    {
        
        tableCards.add(meld);

    }

    public static void addToMeld(ArrayList<Card> toAdd, int tableCardIndex)
    {
        ArrayList<Card> addCardsHere = tableCards.remove(tableCardIndex);
        for (Card c : toAdd) 
        {
            addCardsHere.add(c);
        }
        
        tableCards.add(addCardsHere);
    }
}
