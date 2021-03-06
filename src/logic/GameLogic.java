package logic;

import data.*;

import java.util.ArrayList;

public class GameLogic {

    private ArrayList<Player> players;
    private ArrayList<Card> communityCards;
    private Deck deck;
    private final int MAX_BOTS = 7;
    private int currentPot;
    private int highestBet;

    public GameLogic(int numBots, String playerName) {
        this.players = new ArrayList<>();
        this.communityCards = new ArrayList<>();
        this.deck = new Deck();
        addPlayers(numBots);
        getPlayer(0).setName(playerName);
    }

    private void addPlayers(int numPlayers) {
        if (numPlayers < 1) {
            numPlayers = 1;
        } else if (numPlayers > MAX_BOTS) {
            numPlayers = MAX_BOTS;
        }
        for (int i = 0; i <= numPlayers; i++) {
            players.add(new Player());
        }
    }

    public Player getPlayer(int idx) {
        return players.get(idx);
    }

    public int numPlayers() {
        return players.size();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Card> getCommunityCards() {
        return communityCards;
    }

    public void dealPlayers() {
        for (Player player : players) {
            player.addCard(deck.dealCard());
            player.addCard(deck.dealCard());
        }
    }

    private void dealCommunity(int numCards) {
        for (int i = 0; i < numCards; i++) {
            communityCards.add(deck.dealCard());
        }
    }

    public void flop() {
        dealCommunity(3);
    }

    public void turn() {
        dealCommunity(1);
    }

    public void river() {
        dealCommunity(1);
    }

    public ArrayList<Player> getWinners() {
        ArrayList<Player> winners = new ArrayList<>();
        Hand bestHand = new Hand();

        for (Player player : players) {
            Hand playerHand = player.getHand();
            playerHand.addList(communityCards);
            BestHand.set(playerHand);

            if (!player.hasFolded() && playerHand.isBetterHand(bestHand)) {
                bestHand = playerHand;
                winners = new ArrayList<>();
                winners.add(player);
            } else if (!player.hasFolded() && playerHand.isDraw(bestHand)) {
                winners.add(player);
            }
        }

        for (Player winner : winners) {
            System.out.println(winner.getName());
            winner.getHand().printCards();
        }

        return winners;
    }

    /** Reset all player hands, reset community cards, create new deck. */
    public void resetRound() {
        for (Player player : players) {
            player.newRound();
        }
        this.communityCards = new ArrayList<>();
        this.deck = new Deck();
    }
}
