/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sheridancollege.project;

/**
 *@author Nima
 */
import java.util.LinkedList;
import java.util.Random;

public class Decks 
{
    //A deck containing 52 cards.
    //Data for a Deck Object
    private LinkedList<Card> deck;
    private Card c;

    //Make a new Deck of Card Objects(52Cards) and store it in a LinkedList
    //to use as a Stack.
    public LinkedList<Card> makeDeck()
    {
        this.deck = new LinkedList<Card>();
        for (Suit s : Suit.values())
        {
            for (Ranks r : Ranks.values())
            {
                this.c = new Card(r, s);
                this.deck.push(this.c);
            }
        }
        return this.deck;
    }

    //Method to shuffle the new generated Deck of Cards.
    public void shuffle(LinkedList<Card> deckCards)
    {
        Random rnd = new Random();
        //Iterate backwards through the cards
        for (int i = deckCards.size() - 1; i > 0; i--)
        {
            //generate a random number within the stack
            int index = rnd.nextInt(i + 1);
            //Generate 2 cards at two different placeholders (one random
            //the other one through the iteration cycle)
            Card firstCard = deckCards.get(index);
            Card secondCard = deckCards.get(i);
            // Remove the card at the current placeholder and
            // replace it with the one from the other placeholder(Swap them out).
            deckCards.remove(firstCard);
            deckCards.add(index, secondCard);
            deckCards.remove(secondCard);
            deckCards.add(i, firstCard);
        }
    }

    //Method to deal the Card, which removes the first of the Stack
    //and show it. Also check if there are still cards in the Stack
    public Card deal(LinkedList<Card> decksCard)
    {
        //Removes and returns the top card or None
        //if the deck is empty.
        if (decksCard.isEmpty())
        {
            System.out.println("The deck is empty");
            return null;
        }
        else 
        {
            return decksCard.pop();
        }
    }
}

