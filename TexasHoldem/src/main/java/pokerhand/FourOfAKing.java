package pokerhand;

import model.Card;
import model.CardComparator;
import model.Player;

public class FourOfAKing extends PokerHand {
	private static final int rank = 8;
	private Card four;
	
	public FourOfAKing(Player player, Card four) {
		super(player); 
		this.four = four;
	}
	public Card getFour() {
		return four;
	}
	@Override
	public int getRank() {
		return rank;
	}
	@Override
	public int subCompare(PokerHand o1, PokerHand o2) {
		return new CardComparator().compare(((FourOfAKing)o1).getFour(), ((FourOfAKing)o2).getFour());
	}

}
