package model;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import exceptions.TooLowDeckSizeException;
import exceptions.WrongColorException;
import exceptions.WrongNameException;	

public class Deck {
	private List<Card> deck;
	public Deck() {
		deck=new ArrayList<Card>();
	}
	public void fillDeckWithAllCards() {
		deck = new ArrayList<Card>();
		for (int i=0 ; i<Card.availableNames.length ; i++) {
			for(int j=0 ; j<Card.availableColors.length ; j++) {
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
	public Card withdrawCard() throws TooLowDeckSizeException {
		if(deck.size()<1)
			throw new TooLowDeckSizeException();
		Card outcome = deck.get(deck.size()-1);
		deck.remove(0);
		return outcome;
	}
	public List<Card> withdrawCards(int count) throws TooLowDeckSizeException {
		if(count>deck.size())
			throw new TooLowDeckSizeException();
		List<Card> outcome = new ArrayList<Card>();
		for(int i=0 ; i<count ; i++) {
			outcome.add(deck.get(deck.size()-1));
			deck.remove(deck.size()-1);
		}
		return outcome;
	}
	public void addCard(Card c) {
		deck.add(c);
		//deck.add(deck.size()-1, c);
	}
	public void addCards(List<Card> cards) {
		deck.addAll(cards);
	}
	/*
	 * niepodoba mi si� ta funkcja w tej klasie
	 
	private void dealCards(List<Player> players, int cardsPerPlayer) throws TooLowDeckSizeException {
		if(deck.size() < cardsPerPlayer*players.size())
			throw new TooLowDeckSizeException();
		for(int i=0 ; i<players.size() ; i++) {
			players.get(i).addCards(withdrawCards(cardsPerPlayer));
		}
	}
	*/
	public void shuffleCards() {
		List<Card> list1 = deck;
		deck.clear();
		Random rand = new Random();
		int n;
		while(list1.size() > 0) {
			n=rand.nextInt(list1.size());
			deck.add(list1.remove(n));
		}		
	}
	// poprawic
	private int getCardIndex(Card c) {
		int i=0;
		boolean notFound = true;
		while(i<Card.availableNames.length && notFound) {
			
		}
		return 1;
	}
}
