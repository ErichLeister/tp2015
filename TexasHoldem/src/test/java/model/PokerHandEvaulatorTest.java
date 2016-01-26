package model;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;
import org.mockito.Mockito;

import exceptions.WrongColorException;
import exceptions.WrongNameException;
import pokerhand.FourOfAKing;
import pokerhand.FullHouse;

public class PokerHandEvaulatorTest {

	@Test
	public void testGroupCards() throws NoSuchMethodException, SecurityException,
	WrongColorException, WrongNameException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		List<Card> l = new ArrayList<Card>();

		PokerHandEvaulator phe = new PokerHandEvaulator();//Mockito.mock(PokerHandEvaulator.class);
//		
//		Method m = (PokerHandEvaulator.class.getClass()).getMethod("groupCards", l.getClass());
//		m.setAccessible(true);
		
		l.add(new Card("5", "kier"));
		l.add(new Card("W", "pik"));
		l.add(new Card("9", "trefl"));
		l.add(new Card("5", "karo"));
		l.add(new Card("2", "kier"));
		l.add(new Card("5", "pik"));
		l.add(new Card("W", "kier"));

		Map<Card, List<Card>> output = (TreeMap<Card, List<Card>>) phe.groupCards(l);//(TreeMap<Card, List<Card>>) m.invoke(phe, l);

		List<Card> keys = new ArrayList<Card>();

		keys.addAll(output.keySet());
		Collections.sort(keys, new CardComparator());
		
		List<Card> l0 = output.get(keys.get(0));
		List<Card> l1 = output.get(keys.get(1));
		List<Card> l2 = output.get(keys.get(2));
		List<Card> l3 = output.get(keys.get(3));
	
		if(!(l0.size() == 1) && l0.contains(new Card("2", "kier")))
			fail();
		if(!(l1.size() == 3) && l1.contains(new Card("5", "kier")) && l1.contains(new Card("5", "pik")) &&
				l1.contains(new Card("5", "karo")))
			fail();
		if(!(l2.size() == 1) && l2.contains(new Card("9", "trefl")))
			fail();
		if(!(l3.size() == 2) && l3.contains(new Card("W", "kier")) && l3.contains(new Card("W", "pik")))
			fail();
	}

	@Test
	public void testFindStraightFlush() throws WrongColorException, WrongNameException {
		List<Card> l1, l2, l3, l4, l5, l6;
		Card c1, c2, c3, c4, c5, c6, c7;
		Player p = Mockito.mock(Player.class);
		PokerHandEvaulator phe = new PokerHandEvaulator();
		
		l1 = new ArrayList<Card>();
		l2 = new ArrayList<Card>();
		l3 = new ArrayList<Card>();
		l4 = new ArrayList<Card>();
		l5 = new ArrayList<Card>();
		l6 = new ArrayList<Card>();
		
		c1 = new Card("5", "pik");
		c2 = new Card("8", "kier");
		c3 = new Card("7", "kier");
		c4 = new Card("9", "kier");
		c5 = new Card("K", "pik");
		c6 = new Card("6", "kier");
		c7 = new Card("5", "kier");
		
		l1.add(c1);
		l1.add(c7);
		l2.add(c2);
		l3.add(c3);
		l4.add(c4);
		l5.add(c5);
		l6.add(c6);
		
		Map<Card,List<Card>> arg = new TreeMap<Card,List<Card>>(new CardComparator());
		arg.put(c1, l1);
		arg.put(c2, l2);
		arg.put(c3, l3);
		arg.put(c4, l4);
		arg.put(c5, l5);
		arg.put(c6, l6);
		
		assertEquals(phe.findStraightflush(p, arg) != null , true);
	}
	
	@Test
	public void testFindFourOfAking() throws WrongColorException, WrongNameException {
		List<Card> l1, l2, l3;
		Card c1, c2, c3, c4, c5, c6, c7;
		Player p = Mockito.mock(Player.class);
		PokerHandEvaulator phe = new PokerHandEvaulator();
		
		l1 = new ArrayList<Card>();
		l2 = new ArrayList<Card>();
		l3 = new ArrayList<Card>();
		
		c1 = new Card("5", "pik");
		c2 = new Card("8", "kier");
		c3 = new Card("K", "kier");
		c4 = new Card("8", "karo");
		c5 = new Card("K", "pik");
		c6 = new Card("8", "pik");
		c7 = new Card("8", "trefl");
		
		l1.add(c1);
		l2.add(c2);
		l2.add(c4);
		l2.add(c6);
		l2.add(c7);
		l3.add(c3);
		l3.add(c5);
		
		Map<Card,List<Card>> arg = new TreeMap<Card,List<Card>>(new CardComparator());
		arg.put(c1, l1);
		arg.put(c2, l2);
		arg.put(c3, l3);
		
		FourOfAKing output = (FourOfAKing) phe.findFourOfAKing(p, arg);

		if(output == null || !output.getFour().getName().equals("8"))
			fail();
	}
	
	@Test
	public void testFindFullHouse() throws WrongColorException, WrongNameException {
		List<Card> l1, l2, l3, l4;
		Card c1, c2, c3, c4, c5, c6, c7;
		Player p = Mockito.mock(Player.class);
		PokerHandEvaulator phe = new PokerHandEvaulator();
		
		l1 = new ArrayList<Card>();
		l2 = new ArrayList<Card>();
		l3 = new ArrayList<Card>();
		l4 = new ArrayList<Card>();
		
		c1 = new Card("5", "pik");
		c2 = new Card("8", "kier");
		c3 = new Card("K", "kier");
		c4 = new Card("8", "karo");
		c5 = new Card("K", "pik");
		c6 = new Card("7", "pik");
		c7 = new Card("8", "trefl");
		
		l1.add(c1);
		l2.add(c2);
		l2.add(c4);
		l2.add(c7);
		l4.add(c6);
		l3.add(c3);
		l3.add(c5);
		
		Map<Card,List<Card>> arg = new TreeMap<Card,List<Card>>(new CardComparator());
		arg.put(c1, l1);
		arg.put(c2, l2);
		arg.put(c3, l3);
		arg.put(c6, l4);
		
		FullHouse output = (FullHouse) phe.findFullHouse(p, arg);

		if(output == null || !output.getThree().getName().equals("8"))
			fail();
	}
}
