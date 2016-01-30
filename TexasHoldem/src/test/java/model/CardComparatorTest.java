package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import exceptions.NotSameListsLengthException;
import exceptions.WrongColorException;
import exceptions.WrongNameException;

public class CardComparatorTest {

	CardComparator cc;
	Card c1, c2, c3, c4, c5, c6;
	List<Card> l1, l2;
	
	@Test
	public void testCompare() throws WrongColorException, WrongNameException {
		assertEquals(new CardComparator().compare(new Card("4", "kier"), new Card("W", "pik")), -1);
		
	}
	
	@Test
	public void testSort() throws WrongColorException, WrongNameException {
		cc = new CardComparator();
		
		c1 = new Card("4", "kier");
		c2 = new Card("W", "pik");
		c3 = new Card("3", "karo");
		
		List<Card> l = new ArrayList<Card>();
		
		l.add(c1);
		l.add(c2);
		l.add(c3);
		
		Collections.sort(l, cc);

		if(!(l.get(0).equals(c3) && l.get(1).equals(c1) && l.get(2).equals(c2)))
			fail();
	}
	
	@Test
	public void testCompareCardLists() throws NotSameListsLengthException, WrongColorException, WrongNameException {
		cc = new CardComparator();
		c1 = new Card("4", "kier");
		c2 = new Card("W", "pik");
		c3 = new Card("3", "karo");
		c4 = new Card("D", "pik");
		c5 = new Card("4", "kier");
		c6 = new Card("W", "pik");
		List<Card> l1 = new ArrayList<Card>();
		List<Card> l2 = new ArrayList<Card>();
		
		l1.add(c1);
		l1.add(c2);
		l1.add(c3);
	
		l2.add(c4);
		l2.add(c5);
		l2.add(c6);
		assertEquals(cc.compareCardLists(l1, l2, cc), -1);
	}

}
