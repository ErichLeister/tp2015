package pokerhand;

import model.Card;
import model.CardComparator;
import model.Player;

public class ThreeOfAKing extends PokerHand {
	private static final int rank = 4;
	private Card three;
	
	public ThreeOfAKing(Player player, Card three) {
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
		return new CardComparator().compare(((ThreeOfAKing)o1).getThree(), ((ThreeOfAKing)o2).getThree());
	}

}
