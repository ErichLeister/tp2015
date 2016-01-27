package pokerhand;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import exceptions.PokerHandException;
import exceptions.WrongColorException;
import exceptions.WrongNameException;
import model.Card;
import model.Player;

public class OnePairTest {

	@Test
	public void testSubCompareWithSamePairs() throws WrongColorException, WrongNameException, PokerHandException {
		List<Card> l1 = new ArrayList<Card>();
		List<Card> l2 = new ArrayList<Card>();
		
		l1.add(new Card("3","kier"));
		l1.add(new Card("K", "pik"));
		l1.add(new Card("2", "karo"));
		
		l2.add(new Card("W","kier"));
		l2.add(new Card("4", "pik"));
		l2.add(new Card("7", "karo"));
		
		Card pair1 = new Card("D", "pik");
		Card pair2 = new Card("5", "kier");
		
		Player p1 = Mockito.mock(Player.class);
		Player p2 = Mockito.mock(Player.class);

		OnePair op1 = new OnePair(p1, pair1, l1);
		OnePair op2 = new OnePair(p2, pair2, l2);

		assertEquals(op1.subCompare(op1, op2), 1);
	}
	
	@Test
	public void testSubCompareWithDifferentPairs() throws WrongColorException, WrongNameException, PokerHandException {
		List<Card> l1 = new ArrayList<Card>();
		List<Card> l2 = new ArrayList<Card>();
		
		l1.add(new Card("3","kier"));
		l1.add(new Card("K", "pik"));
		l1.add(new Card("2", "karo"));
		
		l2.add(new Card("W","kier"));
		l2.add(new Card("4", "pik"));
		l2.add(new Card("7", "karo"));
		
		Card pair = new Card("D", "pik");
		Player p1 = Mockito.mock(Player.class);
		Player p2 = Mockito.mock(Player.class);

		OnePair op1 = new OnePair(p1, pair, l1);
		OnePair op2 = new OnePair(p2, pair, l2);

		assertEquals(op1.subCompare(op1, op2), 1);
	}

	@Test
	public void shouldThrowConstructorExceptionTest() throws WrongColorException, WrongNameException {
		List<Card> l = new ArrayList<Card>();
		l.add(new Card("3","kier"));
		l.add(new Card("K", "pik"));
		l.add(new Card("2", "karo"));
		
		Player p  = Mockito.mock(Player.class);
		try {
			new OnePair(p, new Card("2", "pik"), l);
			fail();
		} catch (PokerHandException e) {}
	}

}
