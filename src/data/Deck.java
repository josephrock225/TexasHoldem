package data;

import enums.CardSuite;
import enums.CardValue;

import java.util.ArrayList;
import java.util.Random;

public class Deck {

    private final ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        makeDeck();
    }

    private void makeDeck() {
        for (CardSuite suite : CardSuite.values()){
            for (CardValue value : CardValue.values()) {
                cards.add( new Card( value.toInt(), suite.toString(), value.toString()) );
            }
        } 
    }

    public Card dealCard() {
        Random r = new Random();
        int randomIndex = r.nextInt(cards.size());

        Card card = cards.get(randomIndex);
        cards.remove(randomIndex);

        return card;
    }

    public Card getCard(CardSuite suite, CardValue value) {
        Card card = new Card();

        for (Card c : cards) {
            if (c.getSuite().equals(suite.toString()) && c.getValueAsString().equals(value.toString())) {
                card = c;
                break;
            }
        }

        return card;
    }
}
