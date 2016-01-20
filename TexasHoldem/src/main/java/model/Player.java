package model;

public class Player {
	private Deck cardsInHand;
	private String name;
	
	public Player(String name) {
		cardsInHand = null;
		this.name = name;
	}
	
	public Player(Deck cards) {
		this.cardsInHand = cards;
	}
	
	public String getName() {
		return name;
	}
	
	public Deck getCardsInHand() {
		return cardsInHand;
	}
}
