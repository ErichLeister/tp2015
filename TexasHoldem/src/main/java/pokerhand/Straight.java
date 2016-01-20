package pokerhand;

import model.Card;
import model.CardComparator;
import model.Player;

public class Straight extends PokerHand {
	
	private static final int rank = 5;
	private Card highestCard;
	
	public Straight(Player player, Card highestCard) {
		super(player);
		this.highestCard = highestCard;
	}
	@Override
	public int getRank() {
		return rank;
	}

	@Override
	public int subCompare(PokerHand o1, PokerHand o2) {
		return new CardComparator().compare(((Straight)o1).highestCard, ((Straight)o2).highestCard);
	}
	

}
