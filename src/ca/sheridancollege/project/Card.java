
package ca.sheridancollege.project;


public class Card implements Comparable<Card> 
{
    //Data for a card object
    private Ranks rank;
    private Suit suit;

    //Constructor
    public Card(Ranks rank, Suit suit)
    {
        this.rank = rank;
        this.suit = suit;
    }

    public Ranks getRank() 
    {
        return this.rank;
    }

    public Suit getSuit() 
    {
        return this.suit;
    }

    //Method that returns the string representation of a card.
    @Override
    public String toString()
    {
        String shortRankString = this.rank.getShortString();        //get card rank short string representation
        char suit = this.suit.getSymbol();                          //get card suit symbol
        String color = this.suit.getColor();                        //get suit color (red/black)
        String defaultColor = "\u001B[0m";                          //ANSI default color

        return color + shortRankString + suit + defaultColor;
    }

    public int compareTo(Card otherCard) 
    {
        if (this.rank.getValue() < otherCard.rank.getValue()) 
        {
            //lower ranks at the front
            return -1;
        } else if (this.rank.getValue() > otherCard.rank.getValue()) 
        {
            //higher ranks at the back
            return 1;
        } else 
        {
            return 0;
        }
    }
}