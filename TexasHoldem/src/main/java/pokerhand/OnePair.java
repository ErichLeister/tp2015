package pokerhand;

import java.util.List;

import exceptions.NotSameListsLengthException;
import exceptions.PokerHandException;
import model.Card;
import model.CardComparator;
import model.Player;

public class OnePair extends PokerHand {
	private static final int rank = 2;
	private Card pair;
	private List<Card> rest;
	
	public OnePair(Player player, Card pair, List<Card> rest) throws PokerHandException {
		super(player);
		if(!(rest.size() == 3))
			throw new PokerHandException(this);
		
		for(Card c : rest) {
			if(c.getName().equals(pair.getName()))
				throw new PokerHandException(this);
		}
		this.pair = pair;
		this.rest = rest;
	}
	public Card getPair() {
		return pair;
	}
	public List<Card> getRest() {
		return rest;
	}

	@Override
	public int getRank() {
		return rank;
	}

	@Override
	public int subCompare(PokerHand o1, PokerHand o2) {
		CardComparator cc = new CardComparator();
		
		OnePair op1 = (OnePair)o1;
		OnePair op2 = (OnePair)o2;
		System.out.println("b");
		int pairValueComp = cc.compare(op1.getPair(), op2.getPair());
		if (pairValueComp != 0)
			return pairValueComp;
		else {
			System.out.println("c");
			int outcome = 0;
			try {
				System.out.println("d");
				outcome = cc.compareCardLists(op1.getRest(), op2.getRest(), cc);
				System.out.println("e");
			} catch (NotSameListsLengthException e) {}
			return outcome;
		}
	}
}
