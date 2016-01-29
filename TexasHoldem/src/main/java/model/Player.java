package model;

import java.util.ArrayList;
import java.util.List;

import myClientServer.Answer;
import myClientServer.Message;
import myClientServer.RealUser;
import playerState.PlayerState;
import playerState.PlayerStateBehavior;

public class Player {
	private String name;
	private int chips;
	private PlayerStateBehavior playerStateBehavior;
	private RealUser user;
	private List<Card> cards;
	
	public Player(String name, RealUser user){
		this.name = name;
		this.user = user;
		this.chips = 0;
		this.cards = new ArrayList<Card>();
		this.playerStateBehavior = PlayerState.INIT.getStateBehavior();
	}

	public void sendMessage(String msg){
		user.giveMessage(new Message(msg));
	}
	public Answer getAnswer(){
		return user.getAnswer(10);
	}
	public List<Card> getCards() {
		return this.cards;
	}
	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
	public String getName() {
		return this.name;
	}
	public int getChips() {
		return this.chips;
	}
	public void setChips(int chips) {
		this.chips = chips;
	}
	public void setPlayerStateBehavior(PlayerStateBehavior playerStateBehavior) {
		this.playerStateBehavior = playerStateBehavior;
	}
	public PlayerStateBehavior getPlayerStateBehavior() {
		return this.playerStateBehavior;
	}
}
