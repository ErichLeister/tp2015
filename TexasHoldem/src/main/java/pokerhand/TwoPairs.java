package pokerhand;

import exceptions.PokerHandException;
import model.Card;
import model.CardComparator;
import model.Player;

public class TwoPairs extends PokerHand {
	private static final int rank = 3;
	private Card firstPair;
	private Card secondPair;
	private Card kicker;
	
	public TwoPairs(Player player, Card firstPair, Card secondPair, Card kicker) throws PokerHandException{
		super(player);
		this.firstPair = firstPair;
		this.secondPair = secondPair;
		this.kicker = kicker;
	}

	public Card getKicker() {
		return kicker;
	}
	public Card getFirstPair() {
		return firstPair;
	}
	public Card getSecondPair() {
		return secondPair;
	}
	
	@Override
	public int getRank() {
		return rank;
	}

	@Override
	public int subCompare(PokerHand o1, PokerHand o2) {
		TwoPairs tp1 = (TwoPairs)o1;
		TwoPairs tp2 = (TwoPairs)o2;
		CardComparator cc = new CardComparator();
		int firstPairCom = cc.compare(tp1.getFirstPair(), tp2.getFirstPair());
		
		if(firstPairCom != 0)
			return firstPairCom;
		else {
			int secondPairCom = cc.compare(tp1.getSecondPair(), tp2.getSecondPair());
			if(secondPairCom != 0)
				return secondPairCom;
			else
				return cc.compare(tp1.getKicker(), tp2.getKicker());
		}
	}
	

}
