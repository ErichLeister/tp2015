package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import exceptions.PokerHandException;
import pokerhand.Flush;
import pokerhand.FourOfAKing;
import pokerhand.FullHouse;
import pokerhand.OnePair;
import pokerhand.PokerHand;
import pokerhand.StraightFlush;
import pokerhand.ThreeOfAKing;
import pokerhand.TwoPairs;


public class PokerHandEvaulator {
	private List<PokerHand> pokerHand;
	
	public PokerHandEvaulator() {
		this.pokerHand = null;
	}
	
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

	public PokerHand findStraightflush(Player p, Map<Card, List<Card>> cards) {
		CardComparator cc = new CardComparator();
		TreeSet<Card> ts = new TreeSet<Card>(cc);
		ts.addAll(cards.keySet());
		
		Iterator<Card> it = ts.descendingIterator();
		Iterator<Card> itColor;
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
	
	public PokerHand findFourOfAKing(Player p, Map<Card, List<Card>> cards) {
		CardComparator cc = new CardComparator();
		TreeSet<Card> ts = new TreeSet<Card>(cc);
		ts.addAll(cards.keySet());
		
		Iterator<Card> it = ts.descendingIterator();
		boolean isFound = false;
		Card card;
		Card outputCard = null;
		
		while(it.hasNext() && !isFound) {
			card = it.next();
			if(cards.get(card).size() == 4) {
				outputCard = card;
				isFound = true;
			}
		}
		
		return (isFound ? new FourOfAKing(p, outputCard) : null);		
	}
	
	public PokerHand findFullHouse(Player p, Map<Card, List<Card>> cards) {
		CardComparator cc = new CardComparator();
		TreeSet<Card> ts = new TreeSet<Card>(cc);
		ts.addAll(cards.keySet());
		
		Iterator<Card> it = ts.descendingIterator();
		boolean isThreeFound = false;
		boolean isPairFound = false;
		
		Card card;
		Card outputThree = null;
		
		while(it.hasNext() && !(isThreeFound && isPairFound)) {
			card = it.next();
			if(!isThreeFound && cards.get(card).size() == 3) {
				outputThree = card;
				isThreeFound = true;
			}
			if(!isPairFound && cards.get(card).size() == 2) {
				isPairFound = true;
			}
		}
		return (isThreeFound && isPairFound ? new FullHouse(p, outputThree) : null);
	}
	
	public PokerHand findFlush(Player p, Map<Card, List<Card>> cards) {
		CardComparator cc = new CardComparator();
		TreeSet<Card> ts = new TreeSet<Card>(cc);
		ts.addAll(cards.keySet());
		
		Iterator<Card> it = ts.descendingIterator();
		Iterator<Card> itColor = null;

		boolean isFound = false;
		
		List<Card> treflCards = new ArrayList<Card>();
		List<Card> karoCards = new ArrayList<Card>();
		List<Card> pikCards = new ArrayList<Card>();
		List<Card> kierCards = new ArrayList<Card>();
		
		Card card;
		Card card1; //template card in a loop inside a list in the map 
		
		List<Card> output = null;

		while(it.hasNext() && !isFound) {
			card = it.next();
			itColor = cards.get(card).iterator();
			
			while(itColor.hasNext()) {
				card1 = itColor.next();
				
				if(card1.getColor().equals("trefl")) {
					treflCards.add(card1);
					if(treflCards.size() == 5) {
						isFound = true;
						output = treflCards;
					}
				}
				else if(card1.getColor().equals("karo")) {
					karoCards.add(card1);
					if(karoCards.size() == 5) {
						isFound = true;
						output = karoCards;
					}
				}
				else if(card1.getColor().equals("pik")) {
					pikCards.add(card1);
					if(pikCards.size() == 5) {
						isFound = true;
						output = pikCards;
					}
				}
				else if(card1.getColor().equals("kier")) {
					kierCards.add(card1);
					if(kierCards.size() == 5) {
						isFound = true;
						output = kierCards;
					}
				}
			}		
		}
		if(isFound) {
			Flush outcome = null;
			try {
				outcome = new Flush(p, output);
			} catch (PokerHandException e) {
				e.printStackTrace();
			}
			return outcome;
		}
		else
			return null;
	}

	public PokerHand findThreeOfAKing(Player p, Map<Card, List<Card>> cards) {
		CardComparator cc = new CardComparator();
		TreeSet<Card> ts = new TreeSet<Card>(cc);
		ts.addAll(cards.keySet());
		
		Iterator<Card> it = ts.descendingIterator();
		boolean isFound = false;
		Card card;
		Card outputCard = null;
		
		while(it.hasNext() && !isFound) {
			card = it.next();
			if(cards.get(card).size() == 3) {
				outputCard = card;
				isFound = true;
			}
		}
		return (isFound ? new ThreeOfAKing(p, outputCard) : null);		
	}
	
	public PokerHand findTwoPairs(Player p, Map<Card, List<Card>> cards) {
		CardComparator cc = new CardComparator();
		TreeSet<Card> ts = new TreeSet<Card>(cc);
		ts.addAll(cards.keySet());
		
		Iterator<Card> it = ts.descendingIterator();
		boolean isHigherPairFound = false;
		boolean isLowerPairFound = false;
		boolean isKickerFound = false;
		
		Card card;
		Card HPair = null;
		Card LPair = null;
		Card Kicker = null;
		
		while(it.hasNext() && !(isHigherPairFound && isLowerPairFound && isKickerFound)) {
			card = it.next();
			if(!isHigherPairFound && cards.get(card).size() == 2) {
				HPair = card;
				isHigherPairFound = true;
				continue;
			}
			if(!isLowerPairFound && cards.get(card).size() == 2) {
				LPair = card;
				isLowerPairFound = true;
				continue;
			}
			if(!isKickerFound) {
				Kicker = card;
				isKickerFound = true;
			}
		}
		return (isHigherPairFound && isLowerPairFound && isKickerFound? new TwoPairs(p, HPair, LPair, Kicker) : null);
	}
		
	public PokerHand findOnePair(Player p, Map<Card, List<Card>> cards) {
		CardComparator cc = new CardComparator();
		TreeSet<Card> ts = new TreeSet<Card>(cc);
		ts.addAll(cards.keySet());
		
		Iterator<Card> it = ts.descendingIterator();
		boolean isPairFound = false;
		boolean isRestReady = false;
		List<Card> rest = new ArrayList<Card>();
		
		Card card;
		Card pair = null;
		
		while(it.hasNext() && !(isPairFound && isRestReady)) {
			card = it.next();
			if(!isPairFound && cards.get(card).size() == 2) {
				pair = card;
				isPairFound = true;
			}
			else {
				rest.add(card);
				if(rest.size() == 3)
					isRestReady = true;
			}
			
		}
			
		if(isPairFound && isRestReady) {
			PokerHand outcome = null;
			try {
				outcome = new OnePair(p, pair, rest);
			}
			catch(PokerHandException e) {}
			return outcome;
		}
		else 
			return null;
		
	}		
}