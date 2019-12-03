/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sheridancollege.project;

/**
 *
 * @author Nima
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;

public class Hand 
{
    private ArrayList<Card> Card;

    public Hand() 
    {
        this.Card = new ArrayList<Card>();
    }

    public ArrayList<Card> getAllCards() 
    {
        return this.Card;
    }

    public Card getCard(int index) 
    {
        return this.Card.get(index);
    }

    public void AddCard(Card card) 
    {
        this.Card.add(card);
    }

    public void Discard(Card card) 
    {
        DiscardPile.addToDiscard(card);
        Card.remove(card);
    }
    public void Remove(Card card)
    {
        Card.remove(card);
    }

}
