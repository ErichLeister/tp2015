package model;

import static org.junit.Assert.fail;

import exceptions.NotEnoughCardsException;
import exceptions.NotPositiveAmountException;
import exceptions.WrongColorException;
import exceptions.WrongNameException;
import myClientServer.RealUser;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class DeckTest {

  @Test
  public void testPullCards() throws WrongColorException, WrongNameException {
    Card c1 = new Card("W", "kier");
    Card c2 = new Card("9", "trefl");
    Card c3 = new Card("7", "kier");
    
    Deck deck = new Deck();
    deck.addCard(c1);
    deck.addCard(c2);
    deck.addCard(c3);
    
    List<Card> pulledCards = null;
    try {
      pulledCards = deck.pullCards(2);
    } catch (NotEnoughCardsException e) {
      fail();
    } catch (NotPositiveAmountException e) {
      // Shouldn't be thrown. pullCards method was called with an argument 2.
    }
    
    if (pulledCards.size() != 2) {
      fail();
    }
    
    if (! (pulledCards.contains(c1) && pulledCards.contains(c2))) {
      fail();
    }
  }
  
  @Test
  public void testFillDeckWithAllCards() {
    Deck deck = new Deck();
    deck.fillDeckWithAllCards();
    
    List<Card> cardsFromDeck = null;
    try {
      cardsFromDeck = deck.pullCards(52);
    } catch (NotEnoughCardsException e) {
      fail();
    } catch (NotPositiveAmountException e) {
      // Shouldn't be thrown. pullCards method was called with an argument 52.
    }
    
    Set<Card> cardsInSet = new HashSet<Card>(cardsFromDeck);
    if (cardsInSet.size() != 52) {
      fail();
    }
  }
  
  @Test
  public void testFlushCards() throws WrongColorException, WrongNameException {
    Card c1 = new Card("W", "kier");
    Card c2 = new Card("9", "trefl");
    Card c3 = new Card("7", "kier");
    Card c4 = new Card("2", "pik");
    Card c5 = new Card("9", "kier");
    
    List<Card> cardsBeforeShuffle = new ArrayList<Card>();
    
    cardsBeforeShuffle.add(c1);
    cardsBeforeShuffle.add(c2);
    cardsBeforeShuffle.add(c3);
    cardsBeforeShuffle.add(c4);
    cardsBeforeShuffle.add(c5);
    
    Deck deck = new Deck();
    
    deck.addCard(c1);
    deck.addCard(c2);
    deck.addCard(c3);
    deck.addCard(c4);
    deck.addCard(c5);
    
    deck.shuffleCards();
    
    List<Card> cardsAfterShuffle = null;
    
    try {
      cardsAfterShuffle = new ArrayList<Card>(deck.pullCards(5));
    } catch (NotEnoughCardsException e) {
      fail();
    } catch (NotPositiveAmountException e) {
      // Shouldn't be thrown. pullCards method was called with an argument 5.
    }
    
    if (! (new HashSet<Card>(cardsBeforeShuffle)).equals(new HashSet<Card>(cardsAfterShuffle))) {
      fail();
    }
    
    boolean isAnyChange = false;
    
    Iterator<Card> itBefore = cardsBeforeShuffle.iterator();
    Iterator<Card> itAfter = cardsAfterShuffle.iterator();
    
    while (itBefore.hasNext() && itAfter.hasNext()) {
      if (! (itBefore.next().equals(itAfter.next()))) {
        isAnyChange = true;
      }
    }
    
    if (! isAnyChange) {
      fail();
    }
  }
  
  @Test
  public void testDealCards() throws WrongColorException, WrongNameException {
    Card c1 = new Card("W", "kier");
    Card c2 = new Card("9", "trefl");
    Card c3 = new Card("7", "kier");
    Card c4 = new Card("2", "pik");
    Card c5 = new Card("9", "kier");
    Card c6 = new Card("K", "trefl");
    Card c7 = new Card("3", "pik");
    Card c8 = new Card("A", "karo");
    
    Deck deck = new Deck();
    
    deck.addCard(c1);
    deck.addCard(c2);
    deck.addCard(c3);
    deck.addCard(c4);
    deck.addCard(c5);
    deck.addCard(c6);
    deck.addCard(c7);
    deck.addCard(c8);
    
    List<Player> players = new ArrayList<Player>();
    
    for (int i = 0 ; i < 3 ; i++) {
      players.add(new Player("p" + i + 1, Mockito.mock(RealUser.class)));
    }
    
    try {
      deck.dealCards(players, 2);
    } catch (NotEnoughCardsException e) {
      fail();
    } catch (NotPositiveAmountException e) {
      // Shouldn't be thrown. pullCards method was called with an argument 2.
    }
    
    if ((players.get(0).getCards().size() != 2) || (players.get(1).getCards().size() != 2)
        || (players.get(0).getCards().size() != 2)) {
      fail();
    }
    
    if (! (players.get(0).getCards().contains(c1) && players.get(0).getCards().contains(c2))) {
      fail();
    }
    
    if (! (players.get(1).getCards().contains(c3) && players.get(1).getCards().contains(c4))) {
      fail();
    }
    
    if (! (players.get(2).getCards().contains(c5) && players.get(2).getCards().contains(c6))) {
      fail();
    }
    
    try {
      if (! (deck.pullCard().equals(c7)) && deck.pullCard().equals(c8)) {
        fail();
      }
    } catch (NotEnoughCardsException e) {
      fail();
    }
  }
}
