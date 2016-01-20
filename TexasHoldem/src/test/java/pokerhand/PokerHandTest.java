package pokerhand;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.mockito.*;

import exceptions.NotSameListsLengthException;
import exceptions.WrongColorException;
import exceptions.WrongNameException;
import model.Card;
import model.CardComparator;

import org.junit.Test;

public class PokerHandTest {

//	@Test
//	public void testGetRank() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testSubCompare() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testPokerHand() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetPlayer() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testCompareCardLists() {
		PokerHand ph = Mockito.mock(PokerHand.class, Mockito.CALLS_REAL_METHODS);
		List<Card> l1 = new ArrayList<Card>();
		List<Card> l2 = new ArrayList<Card>();
		
		try {
			l1.add(new Card("2", "trefl"));
			l1.add(new Card("4", "karo"));
			l1.add(new Card("W", "karo"));
		
			l2.add(new Card("3", "pik"));
			l2.add(new Card("4", "kier"));
			l2.add(new Card("W", "pik"));
		
		} catch (WrongColorException e) {
			fail("wrong color");
			e.printStackTrace();
		} catch (WrongNameException e) {
			fail("wrong cname");
			e.printStackTrace();
		}
		
		int outcome = 0;
		try {
			outcome = ph.compareCardLists(l1, l2, new CardComparator());
		} catch (NotSameListsLengthException e) {
			fail("Not same lengths");
			e.printStackTrace();
		}
		System.out.println("a");
		assertEquals(outcome, -1);
	}

}
