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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;


public class PCPlayers extends Players 
{

    public PCPlayers() 
    {
        this.playerHand = new Hand();
    }

  
    public void Play() 
    {

        //Draw
        Draw();


        ArrayList<Card> hand = playerHand.getAllCards();

        ArrayList<Card> spades = new ArrayList<Card>();
        ArrayList<Card> clubs = new ArrayList<Card>();
        ArrayList<Card> hearts = new ArrayList<Card>();
        ArrayList<Card> diamonds = new ArrayList<Card>();

        //Separate hand cards by suit
        for (Card card : hand) 
        {
            Suit cardSuit = card.getSuit();

            switch (cardSuit) 
            {
                case Spades:
                    spades.add(card);
                    break;
                case Clubs:
                    clubs.add(card);
                    break;
                case Hearts:
                    hearts.add(card);
                    break;
                case Diamonds:
                    diamonds.add(card);
                    break;
            }
        }

        //Sort each group of cards
        if (!spades.isEmpty()) 
        {
            Collections.sort(spades);
        }
        if (!clubs.isEmpty()) 
        {
            Collections.sort(clubs);
        }
        if (!hearts.isEmpty()) 
        {
            Collections.sort(hearts);
        }
        if (!diamonds.isEmpty())
        {
            Collections.sort(diamonds);
        }
        HashMap<Suit, ArrayList<Card>> suitGroups = new HashMap<Suit, ArrayList<Card>>();
        suitGroups.put(Suit.Spades, spades);
        suitGroups.put(Suit.Clubs, clubs);
        suitGroups.put(Suit.Hearts, hearts);
        suitGroups.put(Suit.Diamonds, diamonds);


        //put the suit arrays in a list of lists for better checking.
        ArrayList<ArrayList<Card>> suitsList = new ArrayList<ArrayList<Card>>();
        suitsList.add(clubs);
        suitsList.add(spades);
        suitsList.add(hearts);
        suitsList.add(diamonds);

        //Meld if necessary
        meld(suitsList);

        //Lay off if necessary
        Layoff();

        //Discard
        Discard(suitGroups);

    }

    private void Draw() 
    {
        //look at top card on discard pile
        Card topCard = DiscardPile.ShowTopCard();
        //check if already drawn from discard
        boolean draw = false;
        //go through all cards in the hand
        for (Card c : playerHand.getAllCards())
        {
            //if discard top and hand have the same suit
            if (c.getSuit() == topCard.getSuit()) 
            {
                //see if we can add discard to hand to get a run
                if (c.getRank().getValue() + 1 == topCard.getRank().getValue() ||
                        c.getRank().getValue() - 1 == topCard.getRank().getValue())
                {
                    playerHand.AddCard(DiscardPile.Draw());     //draw
                    draw = true;        //we drew
                    break;              //and break
                }
            }
            //if the suit is not the same see if we can add it to make a set
            else if (c.getRank() == topCard.getRank())
            {
                playerHand.AddCard(DiscardPile.Draw());     //draw
                draw = true;        //we drew
                break;              //and break
            }
        }
        //if we didn't draw from the discard pile we take from the stock
        if (!draw)
        {
            playerHand.AddCard(StockPiles.Draw());
        }
    }

    private void meld(ArrayList<ArrayList<Card>> suitsList)
    {
        //check if run worked otherwise look for set
        boolean run = false;
        //make arraylist to put meld in
        ArrayList<Card> meld = new ArrayList<Card>();
        //go through the suit Arrays
        for (ArrayList<Card> s : suitsList)
        {
            //if they have at least 3 cards we can check if we can meld them
            if (s.size() >=3)
            {
                for (Card c : s)
                {
                    //start with the first card in the array to compare the rest
                    if (meld.isEmpty()) 
                    {
                        meld.add(c);
                        playerHand.getAllCards().remove(c);
                    }
                    //if the current cards value is one greater then the last one in the array
                    else if (meld.get(meld.size() - 1).getRank().getValue() + 1 == c.getRank().getValue()) 
                    {
                        meld.add(c);    //put to meld list
                        playerHand.getAllCards().remove(c);  //remove from hand
                        Collections.sort(meld); //sort meld list
                    }
                    //if the current cards value is one less then the first one in the array
                    else if (meld.get(0).getRank().getValue() - 1 == c.getRank().getValue()) 
                    {
                        meld.add(c);    //put to meld list
                        playerHand.getAllCards().remove(c);  //remove from hand
                        Collections.sort(meld); //sort meld list
                    }
                    //else retry
                    else 
                    {
                        //if we found at least 3 cards to meld stop searching this array
                        if (meld.size()>=3)
                        {
                            break;
                        }
                        //otherwise put the cards back to the hand if we took them out
                        else 
                        {
                            for (Card a : meld) 
                            {
                                if (!playerHand.getAllCards().contains(a)) 
                                {
                                    playerHand.AddCard(a);
                                }
                            }
                            //clear the current meld list and restart from the current card
                            meld.clear();
                            meld.add(c);                            //add current card
                            playerHand.getAllCards().remove(c);     //remove from hand
                        }
                    }

                }
                //if we previously or at the end of the last array found
                //something to meld then meld it and go on
                if (meld.size() >= 3)
                {
                    Tables.newMeld(meld);
                    System.out.println("PC melds:");
                    System.out.println(meld);
                    run = true;
                    break;
                }
                //otherwise we are at the end of the current array
                //put last checked cards back in the hand and clear the meld
                else {
                    for (Card a : meld) 
                    {
                        if (!playerHand.getAllCards().contains(a)) {
                            playerHand.AddCard(a);
                        }
                    }
                    meld.clear();
                }
            }
        }

        //check for a set if no run
        if (!run)
        {
            //sort the hand to better check for sets
            Collections.sort(playerHand.getAllCards());
            //Iterate over all cards to see if it has a set to meld (reuse meld variable)
            for (Card s : playerHand.getAllCards()){
                //start with the first card in the array to compare the rest
                if (meld.isEmpty()) 
                {
                    meld.add(s);
                }
                //if the rank is the same add
                else if (meld.get(0).getRank() == s.getRank()) 
                {
                    meld.add(s);
                }
                //otherwise put the cards back to the hand if we took them out
                else 
                {
                    //clear the current set list and restart from the current card
                    meld.clear();
                    meld.add(s);
                }
                //if we have a set of 3 (first occurrence)
                if (meld.size() >= 3)
                {
                    //meld it
                    Tables.newMeld(meld);
                    System.out.println("PC melds:");
                    System.out.println(meld);
                    //and remove the cards from the set out of the hand
                    for (Card a : meld) 
                    {
                        playerHand.getAllCards().remove(a);
                    }
                    //stop loop
                    break;
                }
            }

        }

    }

    private void Layoff() 
    {
        ArrayList<ArrayList<Card>> table = Tables.getTableCards();

        for (int x = 0; x < table.size(); x++) 
        {
            //loop through melds on the table
            ArrayList<Card> meld = table.get(x);

            Collections.sort(meld);

            ArrayList<Card> toLayoff = new ArrayList<Card>();

            Card firstCard = meld.get(0);
            Card lastCard = meld.get(meld.size() - 1);

            //get first and last rank values
            int firstCardValue = firstCard.getRank().getValue();
            int lastCardValue = lastCard.getRank().getValue();

            if (firstCardValue == lastCardValue) 
            {
                //same value means different suits (this is a set)
                for (Card c : playerHand.getAllCards()) 
                {
                    if (c.getRank().getValue() == firstCardValue) 
                    {
                        //card is a valid layoff card
                        toLayoff.add(c);
                    }
                }
            } else 
            {
                //different values means same suit (this is a run)
                Suit firstCardSuit = firstCard.getSuit();

                //calculate values for higher and lower layoff cards
                int higherLayoff = lastCardValue + 1;
                int lowerLayoff = firstCardValue - 1;

                for (Card c : playerHand.getAllCards()) 
                {
                    int currCardValue = c.getRank().getValue();

                    if (c.getSuit() == firstCardSuit) 
                    {
                        //card must have same suit
                        if (currCardValue == lowerLayoff) 
                        {
                            //current card is one rank lower than first card in meld
                            toLayoff.add(c);
                            lowerLayoff--;  //higher layoff value increases by one
                        } else if (currCardValue == higherLayoff) 
                        {
                            //current card is one rank higher than last card in meld
                            toLayoff.add(c);
                            higherLayoff++; //lower layoff value decreases by one
                        }
                    }
                }
            }

            for (Card c : toLayoff) 
            {
                //remove cards to layoff from hand
                playerHand.Remove(c);
            }

            //layOff cards to table
            Tables.addToMeld(toLayoff, x);
            System.out.println("PC laid off some cards to table.");
        }
    }

    private void Discard(HashMap<Suit, ArrayList<Card>> suitGroups) 
    {
        Random rand = new Random();

        Card toDiscard;
        ArrayList<Card> discardable = new ArrayList<Card>();
        ArrayList<Card> reserve = new ArrayList<Card>();


        //Measure difference between same-suit cards and act accordingly
        for (Suit suit : suitGroups.keySet())
        {
            ArrayList<Card> cards = suitGroups.get(suit);

            if (!cards.isEmpty()) 
            {
                Collections.sort(cards);

                ArrayList<ArrayList<Card>> subgroups = new ArrayList<ArrayList<Card>>();

                int prevCardIndex = 0;  //start first card in group
                int startSubgroup = 0;  //initiate subgroup first card index

                for (int currCardIndex = 1;             //loop over the rest of the cards
                     currCardIndex < cards.size();      //in the group starting with
                     currCardIndex++) 
                {                 //the second card

                    Card prevCard = cards.get(prevCardIndex);
                    Card currCard = cards.get(currCardIndex);

                    int prevCardRankValue = prevCard.getRank().getValue();
                    int currCardRankValue = currCard.getRank().getValue();

                    int rankDiff = currCardRankValue - prevCardRankValue;

                    //end subgroup and add to subgroups arrayList
                    if (rankDiff > 2) 
                    {
                        ArrayList<Card> subgroup = new ArrayList<Card>();

                        //loop over cards from the start of current subgroup to current card index
                        for (int subgroupCardIX = startSubgroup;
                             subgroupCardIX < currCardIndex;
                             subgroupCardIX++) 
                        {
                            //add each card to new subgroup arrayList
                            subgroup.add(cards.get(subgroupCardIX));
                        }

                        subgroups.add(subgroup);        //add new subgroup to arrayList of subgroups
                        startSubgroup = currCardIndex;  //start new subgroup with current card
                    }

                    prevCardIndex = currCardIndex;
                }

                ArrayList<Card> subgroup = new ArrayList<Card>();

                //loop over cards from the start of current subgroup to end of suit group
                for (int subgroupCardIX = startSubgroup;
                     subgroupCardIX < cards.size();
                     subgroupCardIX++) 
                {
                    //add each card to new subgroup arrayList
                    subgroup.add(cards.get(subgroupCardIX));
                }

                subgroups.add(subgroup);        //add final subgroup to arrayList of subgroups

                //Subgroups with only one card are not a part of a possible run
                //thus are added to reserve cards to be matched for a set
                for (int i = 0; i < subgroups.size(); i++) 
                {
                    ArrayList<Card> sg = subgroups.get(i);
                    if (sg.size() == 1) 
                    {
                        reserve.add(sg.get(0));
                        subgroups.remove(i);
                    }
                }
            }
        }

        //Sort reserve cards
        Collections.sort(reserve);

        //check reserve cards for set matches (cards with the same rank)

        //Split up reserve cards by rank
        HashMap<Ranks, ArrayList<Card>> cardsByRank =
                new HashMap<Ranks, ArrayList<Card>>();

        if (!reserve.isEmpty()) 
        {
            for (int i = 0; i < reserve.size(); i++) 
            {
                Card card = reserve.get(i);
                Ranks cardRank = card.getRank();

                ArrayList<Card> cards = new ArrayList<Card>();

                if (cardsByRank.keySet().contains(cardRank)) 
                {
                    //get cards with same rank
                    cards = cardsByRank.get(cardRank);
                }

                cards.add(card);                        //add card to cards with same rank
                cardsByRank.put(cardRank, cards);       //put all cards with this rank into cardsByRank hashMap
            }

            //Get cards unmatched for a set and put them in "discardable" arrayList
            for (Ranks rank : cardsByRank.keySet()) 
            {
                ArrayList<Card> rankSet = cardsByRank.get(rank);

                if (rankSet.size() == 1) 
                {
                    discardable.add(rankSet.get(0));
                }
            }

            if (discardable.isEmpty())
            {
                //if all reserve cards fall into a set then discard random reserve card
                int randomCard = rand.nextInt(reserve.size());
                toDiscard = reserve.get(randomCard);
            } else 
            {
                //discard random "discardable" card
                int randomCard = rand.nextInt(discardable.size());
                toDiscard = discardable.get(randomCard);
            }
        } else 
        {
            //if there are no reserve (all hand cards fall into a potential run)
            //then discard random card from hand
            if (playerHand.getAllCards().isEmpty())
            {
                toDiscard = null;

            }
            else 
            {
                int randomCard = rand.nextInt(playerHand.getAllCards().size());
                toDiscard = playerHand.getCard(randomCard);
            }
        }

        playerHand.Discard(toDiscard);  //Discard chosen card
    }
}