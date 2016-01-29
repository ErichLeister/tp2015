package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import exceptions.NoPlayersException;
import pokerhand.PokerHand;
import pokerhand.PokerHandComparator;

public class Dealer {
	private List<Card> commonCards;
	private List<Player> players;
	
	public Dealer(List<Card> commonCards, List<Player> players) throws NoPlayersException {
		if(players.size() < 1)
			throw new NoPlayersException();
		this.commonCards = commonCards;
		this.players = players;
	}
	
	//returns a map where number of a place maps a list of players with the same score 
	public Map<Integer, List<Player>> getChartOfWinners() {
		Map<Integer, List<Player>> outcome = new TreeMap<Integer, List<Player>>();
		if(this.players.size() == 1) {
			outcome.put(1, players);
			return outcome;
		}
		PokerHandEvaulator phe = new PokerHandEvaulator();
		List<PokerHand> pokerHands = new ArrayList<PokerHand>();
		
		for(Player p : players) {
			List<Card> cards = p.getCards();
			cards.addAll(commonCards);
			pokerHands.add(phe.returnBestPokerHand(p, cards));
		}
		Collections.sort(pokerHands, Collections.reverseOrder(new PokerHandComparator()));
		
		boolean WasLastPokerHandToPreviosOne = false;
		PokerHandComparator phc = new PokerHandComparator();
		PokerHand ph;
		int place = 1;
		Iterator<PokerHand> it = pokerHands.iterator();
		PokerHand lastPh = it.next();
		List<Player> playersWithSameScore = new ArrayList<Player>();
		playersWithSameScore.add(lastPh.getPlayer());
		
		while(it.hasNext()) {
			ph = it.next();
			if(phc.compare(lastPh, ph) == 0) {
				playersWithSameScore.add(ph.getPlayer());
				WasLastPokerHandToPreviosOne = true;
			}
			else {
				outcome.put(place, playersWithSameScore);
				place++;
				playersWithSameScore = new ArrayList<Player>();
				playersWithSameScore.add(ph.getPlayer());
				WasLastPokerHandToPreviosOne = false;
			}
			lastPh = ph;
		}
		if(WasLastPokerHandToPreviosOne)
			outcome.put(place, playersWithSameScore);
		
		return outcome;
	}
}
