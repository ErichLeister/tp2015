package myClientServer;

import java.io.Serializable;

public class ClientPlayer implements Serializable {
	public String name;
	public String state;
	public int cash;
	public int bet;
	public boolean bigBlind;
	public ClientPlayer(String name, int cash){
		this.name = name;
		this.cash = cash;
		state = "Initial State";
		bet = 0;
		bigBlind = false;
	}
	public void setCash(int cash){
		this.cash = cash;
	}
	public int getCash(){
		return cash;
	}
	public void setBet(int bet){
		this.bet = bet;
	}
	public int getBet(){
		return bet;
	}
	public String getName(){
		return name;
	}
	public void setState(String state){
		this.state = state;
	}
	public String getState(){
		return state;
	}
	public void setBigBlind(boolean b){
		bigBlind = b;
	}
	public boolean getBigBlind(){
		return bigBlind;
	}
}
