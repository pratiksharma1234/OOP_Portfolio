package week5_6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Card {
    private String suit;
    private String value;

    // Constructor 
    public Card(String suit, String value) {
        this.suit = suit;
        this.value = value;
    }

    // Getter methods 
    public String getSuit() {
        return suit;
    }

    public String getValue() {
        return value;
    }

    
    public String toString() {
        return value + " of " + suit;
    }
}

class Deck {
    private List<Card> cards;

    // Constructor to initialize
    public Deck() {
        cards = new ArrayList<>();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        
        // Create the deck of cards
        for (String suit : suits) {
            for (String value : values) {
                cards.add(new Card(suit, value));
            }
        }
    }

    // Shuffle the deck 
    public void shuffle() {
        Collections.shuffle(cards);
    }

    public List<Card> deal(int numberOfCards) {
        List<Card> dealtCards = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            if (cards.isEmpty()) break;
            dealtCards.add(cards.remove(0));         }
        return dealtCards;
    }
}

public class Cards {
    public static void main(String[] args) {
        // Create and shuffle the deck
        Deck deck = new Deck();
        deck.shuffle();

        List<Card> hand = deck.deal(5);

        // Print 
        System.out.println("Dealt Cards:");
        for (Card card : hand) {
            System.out.println(card);
        }
    }
}