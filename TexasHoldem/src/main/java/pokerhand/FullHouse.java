package pokerhand;

import model.Card;
import model.CardComparator;
import model.Player;

public class FullHouse extends PokerHand {
	private static final int rank = 7;
	private Card three;
	
	public FullHouse(Player player, Card three) {
		super(player); 
		this.three = three;
	}
	public Card getThree() {
		return three;
	}
	@Override
	public int getRank() {
		return rank;
	}
	@Override
	public int subCompare(PokerHand o1, PokerHand o2) {
		return new CardComparator().compare(((FullHouse)o1).getThree(), ((FullHouse)o2).getThree());
	}

}
