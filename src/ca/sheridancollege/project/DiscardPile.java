/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sheridancollege.project;

/**
 *
 * @author Prince
 */
import java.util.*;

public class DiscardPile 
{
    private static final LinkedList<Card> discardPile = new LinkedList<Card>();

    public static void toStockPile() 
    {
        Card topCard = discardPile.pop();
        while (discardPile.size() > 0) 
        {
            Card c = discardPile.pop();
            StockPiles.AddCard(c);
        }
        discardPile.push(topCard);
    }
    public static void addToDiscard(Card discard)
    {
        discardPile.push(discard);
    }

    public static Card Draw() 
    {
        return discardPile.pop();
    }

    public static Card ShowTopCard() 
    {
        return discardPile.peek();
    }
}