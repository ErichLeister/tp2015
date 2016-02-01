package model;

import myClientServer.Answer;
import myClientServer.Message;
import myClientServer.RealUser;
import playerstate.PlayerState;
import playerstate.PlayerStateBehavior;

import java.util.ArrayList;
import java.util.List;

public class Player implements PlayerObserver, PlayerObservable {
  private String name;
  private int chips;
  private PlayerStateBehavior playerStateBehavior;
  private RealUser user;
  private List<Card> cards;
  private List<Player> observers;

  public Player(String name, RealUser user) {
    this.name = name;
    this.user = user;
    this.chips = 0;
    this.cards = new ArrayList<Card>();
    this.playerStateBehavior = PlayerState.INIT.getStateBehavior();
    this.observers = new ArrayList<Player>();
  }

  public boolean equals(Object player) {
    return (this.getName().equals(((Player)player).getName()));
  }

  public String toString() {
    return this.name;
  }
  
  public void sendMessage(String msg) {
    user.giveMessage(new Message(msg));
  }
  
  public Answer getAnswer() {
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
  
  public void withdrawChips(int chips) {
    this.chips = this.chips - chips;
  }
  
  public void addCards(List<Card> cards) {
    this.cards.addAll(cards);
  }

  public void setPlayerStateBehavior(PlayerStateBehavior playerStateBehavior) {
    this.playerStateBehavior = playerStateBehavior;
  }
  
  public PlayerStateBehavior getPlayerStateBehavior() {
    return this.playerStateBehavior;
  }

  @Override
  public void addObserver(Player observer) {
    this.observers.add(observer);
  }

  @Override
  public void deleteObserver(Player observer) {
    this.observers.remove(observer);    
  }

  @Override
  public void notifyObservers(String betType) {
    for (Player observer : observers) {
      observer.update(betType);
    }
  }

  @Override
  public void update(String betType) {
    if (betType.equals("rise")) {
      this.playerStateBehavior = this.getPlayerStateBehavior().somebodyRaise();
    } else if (betType.equals("smallBlind")) {
      this.playerStateBehavior = this.getPlayerStateBehavior().somebodySmallBlind();
    } else if (betType.equals("bigBlind")) {
      this.playerStateBehavior = this.getPlayerStateBehavior().somebodyBigBlind();
    }
  }
}
