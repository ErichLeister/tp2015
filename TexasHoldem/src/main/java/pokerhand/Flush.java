package pokerhand;

import java.util.List;

import exceptions.NotSameListsLengthException;
import exceptions.PokerHandException;
import model.Card;
import model.CardComparator;
import model.Player;

public class Flush extends PokerHand {
	private static final int rank = 6;
	private List<Card> cards;
	
	public Flush(Player player, List<Card> cards) throws PokerHandException {
		super(player);
		if(cards.size() != 5)
			throw new PokerHandException(this);
		this.cards = cards;
	}
	public List<Card> getCards() {
		return cards;
	}
	@Override
	public int getRank() {
		return rank;
	}

	@Override
	public int subCompare(PokerHand o1, PokerHand o2) {
		CardComparator cc = new CardComparator();
		List<Card> l1 = ((Flush)o1).getCards();
		List<Card> l2 = ((Flush)o2).getCards();
		
		int outcome = 0;
		
		try {
			outcome = this.compareCardLists(l1, l2, cc);
		} catch (NotSameListsLengthException e) {
			e.getMessage();
		}
		return outcome;
	}

}
