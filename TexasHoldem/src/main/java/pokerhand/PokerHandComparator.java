package pokerhand;

import java.util.Comparator;

public class PokerHandComparator implements Comparator<PokerHand> {
	
	@Override
	public int compare(PokerHand o1, PokerHand o2) {
		if (o1.getClass().getName().equals(o2.getClass().getName()))
			return o1.subCompare(o1,o2);
		else
			return (o1.getRank() > o2.getRank() ? 1 : -1);
	}

}
