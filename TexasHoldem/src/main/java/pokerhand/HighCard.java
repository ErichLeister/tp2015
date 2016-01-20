package pokerhand;

import java.util.ArrayList;
import java.util.List;

import exceptions.NotSameListsLengthException;
import exceptions.PokerHandException;
import model.Card;
import model.CardComparator;
import model.Player;

public class HighCard extends PokerHand{
	List<Card> cards;
	public HighCard(Player player, List<Card> cards) throws PokerHandException {
		super(player);
		this.cards = cards;
		if (cards.size() != 5)
				throw new PokerHandException(this);
	}

	private static final int rank = 1;
	
	@Override
	public int getRank() {
		return rank;
	}
	
	public List<Card> getCards() {
		return cards;
	}

	@Override
	public int subCompare(PokerHand o1, PokerHand o2) {
		ArrayList<Card> l1 = (ArrayList<Card>)((HighCard)o1).getCards();
		ArrayList<Card> l2 = (ArrayList<Card>)((HighCard)o2).getCards();
		int outcome = 0;
		try {
			outcome=this.compareCardLists(l1, l2, new CardComparator());
		}
		catch(NotSameListsLengthException e) {
			System.err.println(e.getMessage());
		}
		return outcome;
	}
}
