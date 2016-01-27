package pokerhand;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;

import exceptions.PokerHandException;
import exceptions.WrongColorException;
import exceptions.WrongNameException;
import model.Card;
import model.Player;

public class TwoPairsTest {

	@Test
	public void testSubCompareWithSameHigherPairs() throws WrongColorException, WrongNameException, PokerHandException {
		
		Card pairH = new Card("3","kier");
		
		Card pairL1 = new Card("K", "pik");
		Card pairL2 = new Card("W","kier");
		
		Card rest1 =  new Card("2", "karo");
		Card rest2 = new Card("4", "pik");
		
		Player p1 = Mockito.mock(Player.class);
		Player p2 = Mockito.mock(Player.class);

		TwoPairs op1 = new TwoPairs(p1, pairH, pairL1, rest1);
		TwoPairs op2 = new TwoPairs(p2, pairH, pairL2, rest2);

		assertEquals(op1.subCompare(op1, op2), 1);
	}
	
	@Test
	public void testSubCompareWithSameLowerPairs() throws WrongColorException, WrongNameException, PokerHandException {
		
		Card pairL = new Card("3","kier");
		
		Card pairH1 = new Card("5", "pik");
		Card pairH2 = new Card("W","kier");
		
		Card rest1 =  new Card("2", "karo");
		Card rest2 = new Card("4", "pik");
		
		Player p1 = Mockito.mock(Player.class);
		Player p2 = Mockito.mock(Player.class);

		TwoPairs op1 = new TwoPairs(p1, pairH1, pairL, rest1);
		TwoPairs op2 = new TwoPairs(p2, pairH2, pairL, rest2);

		assertEquals(op1.subCompare(op1, op2), -1);
	}
	
	@Test
	public void testSubCompareWithSameLowerandHigherPairs() throws WrongColorException, WrongNameException, PokerHandException {
		
		Card pairL = new Card("3","kier");
		Card pairH = new Card("W","kier");
		
		Card rest1 =  new Card("2", "karo");
		Card rest2 = new Card("4", "pik");
		
		Player p1 = Mockito.mock(Player.class);
		Player p2 = Mockito.mock(Player.class);

		TwoPairs op1 = new TwoPairs(p1, pairH, pairL, rest1);
		TwoPairs op2 = new TwoPairs(p2, pairH, pairL, rest2);

		assertEquals(op1.subCompare(op1, op2), -1);
	}

}
