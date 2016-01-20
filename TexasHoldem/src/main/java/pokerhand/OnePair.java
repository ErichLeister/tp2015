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
		
		int pairValueComp =(cc.compare(op1.getPair(), op2.getPair()));
		if (pairValueComp != 0)
			return pairValueComp;
		else {
			int outcome = 0;
			try {
				outcome = this.compareCardLists(op1.getRest(), op2.getRest(), cc);
			}
			catch(NotSameListsLengthException e) {
				System.err.println(e.getMessage());
			}
			return outcome;
		}
	}

}
