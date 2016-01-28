package myClientServer;

public class ClientPlayer {
	public String name;
	public String state;
	public int cash;
	ClientPlayer(String name, int cash){
		this.name = name;
		this.cash = cash;
		state = "Test State";
	}
	public void setCash(int cash){
		this.cash = cash;
	}
	public int getCash(){
		return cash;
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
}
