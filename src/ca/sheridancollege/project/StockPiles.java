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
import java.util.LinkedList;

public class StockPiles 
{
    private static LinkedList<Card> stockPile = new LinkedList<Card>();

    public static Card Draw() 
    {
        return stockPile.pop();
    }

    public static void AddCard(Card card)
    {
        stockPile.push(card);
    }

    public static LinkedList<Card> getStockPile()
    {
        return stockPile;
    }

    public static void setStockPile(LinkedList<Card> cards)
    {
        stockPile = cards;
    }
}
