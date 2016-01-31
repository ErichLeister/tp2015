package model;

import exceptions.NotEnoughCardsException;
import exceptions.NotPositiveAmountException;
import exceptions.WrongColorException;
import exceptions.WrongNameException;   

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
  private List<Card> deck;
  
  public Deck() {
    this.deck = new ArrayList<Card>();
  }
  
  public void fillDeckWithAllCards() {
    deck = new ArrayList<Card>();
    
    for (int i = 0 ; i < Card.availableNames.length ; i++) {
      for (int j = 0 ; j < Card.availableColors.length ; j++) {
        try {
          deck.add(new Card(Card.availableNames[i],Card.availableColors[j]));
        } catch (WrongColorException e) {
          e.printStackTrace();
        } catch (WrongNameException e) {
          e.printStackTrace();
        }
      }
    }
  }
  
  public int getSize() {
    return deck.size();
  }
  
  public Card pullCard() throws NotEnoughCardsException {
    if (deck.size() < 1) {
      throw new NotEnoughCardsException();
    }
    return this.deck.remove(0);
  }
  
  public List<Card> pullCards(int count) throws NotEnoughCardsException, NotPositiveAmountException {
    if (count < 1) {
      throw new NotPositiveAmountException();
    }
    if (count > deck.size()) {
      throw new NotEnoughCardsException();
    }
    
    List<Card> outcome = new ArrayList<Card>();
    
    for (int i = 0 ; i < count ; i++) {
      outcome.add(deck.remove(0));
    }
    return outcome;
  }
  
  public void addCard(Card card) {
    deck.add(card);
  }
  
  public void addCards(List<Card> cards) {
    deck.addAll(cards);
  }
  
  public void dealCards(List<Player> players, int cardsPerPlayer) throws NotEnoughCardsException, NotPositiveAmountException {
    if (deck.size() < cardsPerPlayer * players.size()) {
      throw new NotEnoughCardsException();
    }
    for (Player player : players) {
      player.addCards(this.pullCards(cardsPerPlayer));
    }
  }
  
  public void shuffleCards() {
    List<Card> newDeck = new ArrayList<Card>(deck);
    deck.clear();
    Random rand = new Random();
    int randomIndex;
    
    while (newDeck.size() > 0) {
      randomIndex = rand.nextInt(newDeck.size());
      deck.add(newDeck.remove(randomIndex));
    }
  }
}
