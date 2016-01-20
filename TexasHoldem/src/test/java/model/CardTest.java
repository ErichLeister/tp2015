package model;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.WrongColorException;
import exceptions.WrongNameException;
import model.Card;

public class CardTest {

//	@Test
//	public void testCard() {
//		fail("Not yet implemented");
//	}	
	
	@Test
	public void should_printW_in_getName() {
		Card c = null;
		try {
			c = new Card("W", "kier");
		} catch (WrongColorException | WrongNameException e) {
			e.printStackTrace();
		}
		assertEquals(c.getName(), "W");
	}

	@Test
	public void should_rintKier_in_getColor() {
		Card c = null;
		try {
			c = new Card("W", "kier");
		} catch (WrongColorException | WrongNameException e) {
			e.printStackTrace();
		}
		assertEquals(c.getColor(), "kier");;
	}
	
	@Test
	public void should_throwWrongNameException_when_invalidName() throws WrongColorException {
		try {
			new Card("Jopek", "pik");
			fail("Expected an WrongNameException to be thrown");
		} catch (WrongNameException e) {}
	}
	
	@Test
	public void should_throwWrongColorException_when_invalidColor() throws WrongNameException {
		try {
			new Card("D", "kwadrat");
			fail();
		} catch (WrongColorException e) {}
	}
}
