package pokerhand;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;

import exceptions.WrongColorException;
import exceptions.WrongNameException;
import model.Card;
import model.Player;

public class StraightFlushTest {

	@Test
	public void testSubCompare() throws WrongColorException, WrongNameException {		
		Card t1 = new Card("D", "pik");
		Card t2 = new Card("5", "kier");
		
		Player p1 = Mockito.mock(Player.class);
		Player p2 = Mockito.mock(Player.class);

		StraightFlush op1 = new StraightFlush(p1, t1);
		StraightFlush op2 = new StraightFlush(p2, t2);

		assertEquals(op1.subCompare(op1, op2), 1);
	}
}
