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

public class Players 
{
    //player has cards in his hand
    protected Hand playerHand;

    public Players()
    {
        this.playerHand = new Hand();
    }

    public Hand getPlayerHand() 
    {
        return playerHand;
    }

    //add cards to the hand
    public void addToHand(Card card)
    {
        this.playerHand.AddCard(card);
    }

    //discard card to discard pile
    public void discardFromHand(Card card)
    {
        this.playerHand.Discard(card);
    }

    //Lay out 3 Cards(or take to be added to the table)
    public void melding(ArrayList<Card> meld)
    {
        Tables.newMeld(meld);
    }

}