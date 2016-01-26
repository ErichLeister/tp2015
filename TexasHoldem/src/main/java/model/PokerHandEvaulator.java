package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import pokerhand.PokerHand;
import pokerhand.StraightFlush;


public class PokerHandEvaulator {
	private List<PokerHand> pokerHand;
	
	public PokerHandEvaulator() {
		this.pokerHand = null;
	}
	
//	class PackedCards {
//		Map<String, List<Card>> packedCards;
//		PackedCards(List<Card> cards) {
//			this.packedCards = groupCards.
//		}
//	}
	
	public Map<Card, List<Card>> groupCards(List<Card> cards) {
		CardComparator cc = new CardComparator();
		Collections.sort(cards, cc);
		
		Map<Card, List<Card>> output = new TreeMap<Card, List<Card>>(cc);
		
		Iterator<Card> i = cards.iterator();
		Card c = i.next();
		Card key = c;
		String lastCardsName = key.getName();

		output.put(key, new ArrayList<Card>());
		output.get(key).add(c);

		while(i.hasNext()) {
			c = i.next();
			if(lastCardsName.equals(c.getName())) {
				output.get(key).add(c);
			}
			else {
				key = c;
				output.put(key, new ArrayList<Card>());
				output.get(key).add(c);
				lastCardsName = key.getName();
			}	
		}
		return output;
	}
	
	PokerHand findStraightflush(Player p, Map<Card, List<Card>> cards) {
		CardComparator cc = new CardComparator();
		//Collections.sort((ArrayList)cards, cc);
		TreeSet<Card> ts = new TreeSet<Card>(cc);
		ts.addAll(cards.keySet());
		
		Iterator<Card> it =ts.descendingIterator(); //((TreeSet<Card>)cards.keySet()).descendingIterator();
		Iterator<Card> itColor;
//		List<String> keys = new ArrayList<String>();
//		keys.addAll(cards.keySet());
		boolean isFound = false;
		boolean isFoundColor = false;
		Card card = null;
		Card max = it.next();
		Card last = max;
		
		int i = 1;
		while(it.hasNext() && !isFound) {
			card = it.next();
			if(cc.isSuccessor(last, card)) {
				itColor = cards.get(card).iterator();
				Card cardInside;
				
				while(itColor.hasNext() && !isFoundColor) {
					cardInside = itColor.next();
					if(cardInside.getColor().equals(max.getColor())) {
						i++;
						if(i==5) isFound = true;
						isFoundColor = true;
					}
				}
				if(!isFoundColor) {
					i = 1;
					max = card;
				}
				isFoundColor = false;
			}
			else {
				i = 1;
				max = card;
			}
			last = card;
		}
		return (isFound ? new StraightFlush(p, max) : null);
	}
	
}
