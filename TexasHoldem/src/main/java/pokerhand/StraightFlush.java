package pokerhand;

import model.Card;
import model.CardComparator;
import model.Player;

public class StraightFlush extends PokerHand {
	private static final int rank = 9;
	private Card highestCard;
	
	public StraightFlush(Player player, Card highestCard) {
		super(player);
		this.highestCard = highestCard;
	}
	public Card getHighestCard() {
		return highestCard;
	}
	@Override
	public int getRank() {
		return rank;
	}
	@Override
	public int subCompare(PokerHand o1, PokerHand o2) {
		return new CardComparator().compare(((StraightFlush)o1).getHighestCard(), ((StraightFlush)o2).getHighestCard());
	}

}
