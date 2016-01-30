package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.mockito.Mockito;

import exceptions.NoPlayersException;
import exceptions.WrongColorException;
import exceptions.WrongNameException;
import myClientServer.RealUser;

public class DealerTest {

	@Test
	public void testGetChartOfWinners_withoutAnyDraw() throws WrongColorException, WrongNameException, NoPlayersException {
		List<Card> l1, l2, l3, l4, l5, l6, common;
		Player p1, p2, p3, p4, p5, p6;
		
		l1 = new ArrayList<Card>();
		l2 = new ArrayList<Card>();
		l3 = new ArrayList<Card>();
		l4 = new ArrayList<Card>();
		l5 = new ArrayList<Card>();
		l6 = new ArrayList<Card>();
		common = new ArrayList<Card>();
		
		p1 = new Player("p1", Mockito.mock(RealUser.class));
		p2 = new Player("p2", Mockito.mock(RealUser.class));
		p3 = new Player("p3", Mockito.mock(RealUser.class));
		p4 = new Player("p4", Mockito.mock(RealUser.class));
		p5 = new Player("p5", Mockito.mock(RealUser.class));
		p6 = new Player("p6", Mockito.mock(RealUser.class));
		
		
		common.add(new Card("2", "kier"));
		common.add(new Card("7", "pik"));
		common.add(new Card("W", "pik"));
		common.add(new Card("5", "karo"));
		common.add(new Card("10", "trefl"));
		
		l1.add(new Card("K", "trefl"));
		l1.add(new Card("10", "kier"));
		
		l2.add(new Card("7", "pik"));
		l2.add(new Card("3", "kier"));
		
		l3.add(new Card("4", "pik"));
		l3.add(new Card("D", "karo"));
		
		l4.add(new Card("9", "kier"));
		l4.add(new Card("9", "pik"));
		
		l5.add(new Card("A", "karo"));
		l5.add(new Card("5", "kier"));
		
		l6.add(new Card("2", "trefl"));
		l6.add(new Card("W", "kier"));
		
		p1.setCards(l1);
		p2.setCards(l2);
		p3.setCards(l3);
		p4.setCards(l4);
		p5.setCards(l5);
		p6.setCards(l6);
		
		List<Player> players = new ArrayList<Player>();
		
		players.add(p1);
		players.add(p2);
		players.add(p3);
		players.add(p4);
		players.add(p5);
		players.add(p6);	
		
		Dealer dealer = new Dealer(common, players);
		Map<Integer, List<Player>> outcome = dealer.getChartOfWinners();
		
		if(!((outcome.get(1).size() == 1) && outcome.get(1).contains(p6)))
				fail();
		if(!((outcome.get(2).size() == 1) && outcome.get(2).contains(p1)))
				fail();
		if(!((outcome.get(3).size() == 1) && outcome.get(3).contains(p4)))
				fail();
		if(!((outcome.get(4).size() == 1) && outcome.get(4).contains(p2)))
				fail();
		if(!((outcome.get(5).size() == 1) && outcome.get(5).contains(p5)))
				fail();
		if(!((outcome.get(6).size() == 1) && outcome.get(6).contains(p3)))
				fail();
	}
	
	@Test
	public void testGetChartOfWinners_withADraw() throws WrongColorException, WrongNameException, NoPlayersException {
		List<Card> l1, l2, l3, l4, l5, l6, common;
		Player p1, p2, p3, p4, p5, p6;
		
		l1 = new ArrayList<Card>();
		l2 = new ArrayList<Card>();
		l3 = new ArrayList<Card>();
		l4 = new ArrayList<Card>();
		l5 = new ArrayList<Card>();
		l6 = new ArrayList<Card>();
		common = new ArrayList<Card>();
		
		p1 = new Player("p1", Mockito.mock(RealUser.class));
		p2 = new Player("p2", Mockito.mock(RealUser.class));
		p3 = new Player("p3", Mockito.mock(RealUser.class));
		p4 = new Player("p4", Mockito.mock(RealUser.class));
		p5 = new Player("p5", Mockito.mock(RealUser.class));
		p6 = new Player("p6", Mockito.mock(RealUser.class));
		
		
		common.add(new Card("W", "kier"));
		common.add(new Card("10", "pik"));
		common.add(new Card("9", "pik"));
		common.add(new Card("6", "karo"));
		common.add(new Card("10", "trefl"));
		
		l1.add(new Card("K", "trefl"));
		l1.add(new Card("10", "kier"));
		
		l2.add(new Card("7", "karo"));
		l2.add(new Card("3", "kier"));
		
		l3.add(new Card("4", "pik"));
		l3.add(new Card("D", "karo"));
		
		l4.add(new Card("8", "kier"));
		l4.add(new Card("7", "pik"));
		
		l5.add(new Card("A", "karo"));
		l5.add(new Card("5", "kier"));
		
		l6.add(new Card("7", "trefl"));
		l6.add(new Card("8", "karo"));
		
		p1.setCards(l1);
		p2.setCards(l2);
		p3.setCards(l3);
		p4.setCards(l4);
		p5.setCards(l5);
		p6.setCards(l6);
		
		List<Player> players = new ArrayList<Player>();
		
		players.add(p1);
		players.add(p2);
		players.add(p3);
		players.add(p4);
		players.add(p5);
		players.add(p6);	
		
		Dealer dealer = new Dealer(common, players);
		Map<Integer, List<Player>> outcome = dealer.getChartOfWinners();
		
		System.out.println(outcome.get(1));
		System.out.println(outcome.get(2));
		System.out.println(outcome.get(3));
		System.out.println(outcome.get(4));
		System.out.println(outcome.get(5));
		System.out.println(outcome.get(6));
		
		if(!((outcome.get(1).size() == 2) && outcome.get(1).contains(p4) && outcome.get(1).contains(p6)))
				fail();
		if(!((outcome.get(2).size() == 1) && outcome.get(2).contains(p1)))
				fail();
		if(!((outcome.get(3).size() == 1) && outcome.get(3).contains(p5)))
				fail();
		if(!((outcome.get(4).size() == 1) && outcome.get(4).contains(p3)))
				fail();
		if(!((outcome.get(5).size() == 1) && outcome.get(5).contains(p2)))
				fail();
	}
}
