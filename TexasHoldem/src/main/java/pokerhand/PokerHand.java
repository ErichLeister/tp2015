package pokerhand;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import exceptions.NotSameListsLengthException;
import model.Card;
import model.Player;

public abstract class PokerHand {
	private Player player;
	public abstract int getRank();
	public abstract int subCompare(PokerHand o1, PokerHand o2);
	
	public PokerHand(Player player) {
		this.player = player;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	protected int compareCardLists(List<Card> l1, List<Card> l2, Comparator<Card> cc) 
			throws NotSameListsLengthException {
		
		if(l1.size() != l2.size())
			throw new NotSameListsLengthException();
		
		Collections.sort(l1, cc);
		Collections.sort(l2, cc);
		int outcome = 0;
		int i=l1.size()-1;
		
		do {
			outcome =cc.compare(l1.get(i), l2.get(i));
			i--;
		}
		while((outcome == 0) && (i > 0));
		
		return outcome;
	}
}
