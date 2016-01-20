package model;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import exceptions.LessThanFiveCardsException;
import pokerhand.PokerHand;

public class PokerHandEvaulator {
	private List<PokerHand> pokerHans;
	
	public PokerHandEvaulator() {
		this.pokerHans = null;
	}
	
	private TreeMap<Card, List<Card>> groupCards(List<Card> cards) {
		CardComparator cc = new CardComparator();
		Collections.sort(cards, cc);
		TreeMap<Card, List<Card>> output = new TreeMap<Card, List<Card>>();
		String lastCardsName = null;
		
		Iterator<Card> i = cards.iterator();
		Card key = null;
		List<Card> values = null;
		
		while(i.hasNext()) {
			key = i.next();
			
		}
		//TODO
		return null;
	}
	
	PokerHand findStraightflush(List<Card> cards) throws LessThanFiveCardsException {
		CardComparator cc = new CardComparator();
		Collections.sort(cards, cc);
		Iterator<Card> i = cards.iterator();
		//TODO
		while(true)
		return null;
	}
	
}
