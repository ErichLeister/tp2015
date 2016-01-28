package model;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private String name;
	private int chips;
	private List<Card> cards;
	
	public Player(String name) {
		this.cards = new ArrayList<Card>();
		this.name = name;
		this.chips = 0;
	}
	public Player(String name, int chips) {
		this.cards = new ArrayList<Card>();
		this.name = name;
		this.chips = chips;
	}
	public Player(String name, List<Card> cards) {
		this.cards = cards;
		this.name = name;
		this.chips = 0;
	}
	public Player(String name, List<Card> cards, int chips) {
		this.cards = cards;
		this.name = name;
		this.chips = chips;
	}
	
	public List<Card> getCards() {
		return this.cards;
	}
	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
	public String getName() {
		return name;
	}
	
	public int getChips() {
		return chips;
	}
	public void setChips(int chips) {
		this.chips = chips;
	}
}