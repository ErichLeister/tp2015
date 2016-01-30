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

public class HighCardTest {
	
	@Test
	public void testSubCompare() throws WrongColorException, WrongNameException, PokerHandException {
		List<Card> l1 = new ArrayList<Card>();
		List<Card> l2 = new ArrayList<Card>();
	
		l1.add(new Card("3","kier"));
		l1.add(new Card("K", "pik"));
		l1.add(new Card("2", "karo"));
		l1.add(new Card("D", "pik"));
		l1.add(new Card("4", "kier"));
		
		l2.add(new Card("W","kier"));
		l2.add(new Card("4", "pik"));
		l2.add(new Card("7", "karo"));
		l2.add(new Card("5", "kier"));
		l2.add(new Card("3", "pik"));
	
		Player p1 = Mockito.mock(Player.class);
		Player p2 = Mockito.mock(Player.class);

		HighCard op1 = new HighCard(p1, l1);
		HighCard op2 = new HighCard(p2, l2);

		assertEquals(op1.subCompare(op1, op2), 1);
	}
}
