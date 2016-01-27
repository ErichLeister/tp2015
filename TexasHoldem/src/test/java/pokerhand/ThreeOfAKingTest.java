package pokerhand;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;

import exceptions.WrongColorException;
import exceptions.WrongNameException;
import model.Card;
import model.Player;

public class ThreeOfAKingTest {

	@Test
	public void testSubCompare() throws WrongColorException, WrongNameException {		
		Card t1 = new Card("D", "pik");
		Card t2 = new Card("5", "kier");
		
		Player p1 = Mockito.mock(Player.class);
		Player p2 = Mockito.mock(Player.class);

		ThreeOfAKing op1 = new ThreeOfAKing(p1, t1);
		ThreeOfAKing op2 = new ThreeOfAKing(p2, t2);

		assertEquals(op1.subCompare(op1, op2), 1);
	}

}
