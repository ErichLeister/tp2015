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
import pokerhand.Flush;
import pokerhand.FourOfAKing;
import pokerhand.FullHouse;
import pokerhand.HighCard;
import pokerhand.OnePair;
import pokerhand.PokerHand;
import pokerhand.Straight;
import pokerhand.ThreeOfAKing;
import pokerhand.TwoPairs;

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
	
	@Test
	public void testFindFlush() throws WrongColorException, WrongNameException {
		List<Card> l1, l2, l3, l4, l5;
		Card c1, c2, c3, c4, c5, c6, c7;
		Player p = Mockito.mock(Player.class);
		PokerHandEvaulator phe = new PokerHandEvaulator();
		
		l1 = new ArrayList<Card>();
		l2 = new ArrayList<Card>();
		l3 = new ArrayList<Card>();
		l4 = new ArrayList<Card>();
		l5 = new ArrayList<Card>();
		
		c1 = new Card("5", "pik");
		c2 = new Card("W", "pik");
		c3 = new Card("K", "kier");
		c4 = new Card("8", "karo");
		c5 = new Card("K", "pik");
		c6 = new Card("7", "pik");
		c7 = new Card("8", "pik");
		
		l1.add(c1);
		l2.add(c2);
		l3.add(c3);
		l3.add(c5);
		l4.add(c4);
		l4.add(c7);
		l5.add(c6);
		
		Map<Card,List<Card>> arg = new TreeMap<Card,List<Card>>(new CardComparator());
		arg.put(c1, l1);
		arg.put(c2, l2);
		arg.put(c3, l3);
		arg.put(c4, l4);
		arg.put(c6, l5);
		
		Flush output = (Flush) phe.findFlush(p, arg);

		if(output == null)
			fail();	
	}
	
	@Test
	public void testFindStraight() throws WrongColorException, WrongNameException {
		List<Card> l1, l2, l3, l4, l5;
		Card c1, c2, c3, c4, c5, c6, c7;
		Player p = Mockito.mock(Player.class);
		PokerHandEvaulator phe = new PokerHandEvaulator();
		
		l1 = new ArrayList<Card>();
		l2 = new ArrayList<Card>();
		l3 = new ArrayList<Card>();
		l4 = new ArrayList<Card>();
		l5 = new ArrayList<Card>();
		
		c1 = new Card("5", "pik");
		c2 = new Card("8", "kier");
		c3 = new Card("9", "kier");
		c4 = new Card("8", "karo");
		c5 = new Card("7", "pik");
		c6 = new Card("6", "pik");
		c7 = new Card("6", "trefl");
		
		l1.add(c1);
		l2.add(c2);
		l2.add(c4);
		l3.add(c3);
		l4.add(c5);
		l5.add(c6);
		l5.add(c7);
		
		Map<Card,List<Card>> arg = new TreeMap<Card,List<Card>>(new CardComparator());
		arg.put(c1, l1);
		arg.put(c2, l2);
		arg.put(c3, l3);
		arg.put(c5, l4);
		arg.put(c6, l5);
		
		Straight output = (Straight) phe.findStraight(p, arg);
		
		if(output == null || !output.getHighestCard().getName().equals("9"))
			fail();
	}

	@Test
	public void testFindThreeOfAKing() throws WrongColorException, WrongNameException {
		List<Card> l1, l2, l3, l4, l5;
		Card c1, c2, c3, c4, c5, c6, c7;
		Player p = Mockito.mock(Player.class);
		PokerHandEvaulator phe = new PokerHandEvaulator();
		
		l1 = new ArrayList<Card>();
		l2 = new ArrayList<Card>();
		l3 = new ArrayList<Card>();
		l4 = new ArrayList<Card>();
		l5 = new ArrayList<Card>();
		
		c1 = new Card("5", "pik");
		c2 = new Card("8", "kier");
		c3 = new Card("K", "kier");
		c4 = new Card("8", "karo");
		c5 = new Card("9", "pik");
		c6 = new Card("7", "pik");
		c7 = new Card("8", "trefl");
		
		l1.add(c1);
		l2.add(c2);
		l2.add(c4);
		l2.add(c7);
		l4.add(c6);
		l3.add(c3);
		l5.add(c5);
		
		Map<Card,List<Card>> arg = new TreeMap<Card,List<Card>>(new CardComparator());
		arg.put(c1, l1);
		arg.put(c2, l2);
		arg.put(c3, l3);
		arg.put(c6, l4);
		arg.put(c5, l5);
		
		ThreeOfAKing output = (ThreeOfAKing) phe.findThreeOfAKing(p, arg);
		if(output == null || !output.getThree().getName().equals("8"))
			fail();
		
	}

	@Test
	public void testFindTwoPairs() throws WrongColorException, WrongNameException {
		List<Card> l1, l2, l3, l4, l5;
		Card c1, c2, c3, c4, c5, c6, c7;
		Player p = Mockito.mock(Player.class);
		PokerHandEvaulator phe = new PokerHandEvaulator();
		
		l1 = new ArrayList<Card>();
		l2 = new ArrayList<Card>();
		l3 = new ArrayList<Card>();
		l4 = new ArrayList<Card>();
		l5 = new ArrayList<Card>();
		
		c1 = new Card("5", "pik");
		c2 = new Card("8", "kier");
		c3 = new Card("K", "kier");
		c4 = new Card("8", "karo");
		c5 = new Card("K", "pik");
		c6 = new Card("2", "pik");
		c7 = new Card("4", "trefl");
		
		l1.add(c1);
		l2.add(c2);
		l2.add(c4);
		l3.add(c3);
		l3.add(c5);
		l4.add(c6);
		l5.add(c7);
		
		Map<Card,List<Card>> arg = new TreeMap<Card,List<Card>>(new CardComparator());
		arg.put(c1, l1);
		arg.put(c2, l2);
		arg.put(c3, l3);
		arg.put(c6, l4);
		arg.put(c7, l5);
		
		TwoPairs output = (TwoPairs) phe.findTwoPairs(p, arg);
		
		if(output == null || !output.getFirstPair().getName().equals("K") ||
				!output.getSecondPair().getName().equals("8") || !output.getKicker().getName().equals("5"))
			fail();
	}

	@Test
	public void testFindOnePair() throws WrongColorException, WrongNameException {
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
		c3 = new Card("K", "kier");
		c4 = new Card("8", "karo");
		c5 = new Card("D", "pik");
		c6 = new Card("2", "pik");
		c7 = new Card("4", "trefl");
		
		l1.add(c1);
		l2.add(c2);
		l2.add(c4);
		l3.add(c3);
		l6.add(c5);
		l4.add(c6);
		l5.add(c7);
		
		Map<Card,List<Card>> arg = new TreeMap<Card,List<Card>>(new CardComparator());
		arg.put(c1, l1);
		arg.put(c2, l2);
		arg.put(c3, l3);
		arg.put(c6, l4);
		arg.put(c7, l5);
		arg.put(c5, l6);
		
		OnePair output = (OnePair) phe.findOnePair(p, arg);
		
		if(output == null || !output.getPair().getName().equals("8"))	
			fail();
		ArrayList<Card> rest = (ArrayList<Card>) output.getRest();
		Collections.sort(rest, new CardComparator());

		if(!rest.get(0).getName().equals("5") || !rest.get(1).getName().equals("D") || !rest.get(2).getName().equals("K"))
			fail();		
	}

	@Test
	public void testFindHighCard() throws WrongColorException, WrongNameException {
		List<Card> l1, l2, l3, l4, l5, l6, l7;
		Card c1, c2, c3, c4, c5, c6, c7;
		Player p = Mockito.mock(Player.class);
		PokerHandEvaulator phe = new PokerHandEvaulator();
		
		l1 = new ArrayList<Card>();
		l2 = new ArrayList<Card>();
		l3 = new ArrayList<Card>();
		l4 = new ArrayList<Card>();
		l5 = new ArrayList<Card>();
		l6 = new ArrayList<Card>();
		l7 = new ArrayList<Card>();
		
		c1 = new Card("5", "pik");
		c2 = new Card("8", "kier");
		c3 = new Card("K", "kier");
		c4 = new Card("7", "karo");
		c5 = new Card("D", "pik");
		c6 = new Card("2", "pik");
		c7 = new Card("4", "trefl");
		
		l1.add(c1);
		l2.add(c2);
		l2.add(c2);
		l3.add(c3);
		l6.add(c6);
		l4.add(c4);
		l5.add(c5);
		
		Map<Card,List<Card>> arg = new TreeMap<Card,List<Card>>(new CardComparator());
		arg.put(c1, l1);
		arg.put(c2, l2);
		arg.put(c3, l3);
		arg.put(c4, l4);
		arg.put(c5, l5);
		arg.put(c6, l6);
		arg.put(c7, l7);
		
		HighCard output = (HighCard) phe.findHighCard(p, arg);
		
		if(output == null)	
			fail();
		ArrayList<Card> cards = (ArrayList<Card>) output.getCards();
		Collections.sort(cards, new CardComparator());

		if(!cards.get(0).getName().equals("5") || !cards.get(1).getName().equals("7") || !cards.get(2).getName().equals("8") ||
				!cards.get(3).getName().equals("D") || !cards.get(4).getName().equals("K"))
			fail();
			
	}

	@Test
	public void testReturnBestPokerHand() throws WrongColorException, WrongNameException {
		Card c1, c2, c3, c4, c5, c6, c7;
		Player p = Mockito.mock(Player.class);
		PokerHandEvaulator phe = new PokerHandEvaulator();
		
		List<Card> cards = new ArrayList<Card>();

		
		c1 = new Card("5", "pik");
		c2 = new Card("8", "karo");
		c3 = new Card("K", "kier");
		c4 = new Card("7", "karo");
		c5 = new Card("7", "pik");
		c6 = new Card("2", "pik");
		c7 = new Card("8", "trefl");
		
		cards.add(c1);
		cards.add(c2);
		cards.add(c3);
		cards.add(c4);
		cards.add(c5);
		cards.add(c6);
		cards.add(c7);
		
		PokerHand output = phe.returnBestPokerHand(p, cards);
		
		if(!(output instanceof TwoPairs))
			fail();
	}
}
