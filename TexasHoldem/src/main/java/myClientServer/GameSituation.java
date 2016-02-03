package myClientServer;

import java.util.ArrayList;

public class GameSituation {
	ArrayList <ClientPlayer> players;
	ArrayList <String> cards;

	int chipsOnTable;
	
	public GameSituation(){
		players = new ArrayList<ClientPlayer>();
		cards = new ArrayList<String>();
		int a;
		for(a = 0; a<7; a++){
			cards.add("");
		}
		/*cards.set(0, "A♠");
		cards.set(1, "2♠");
    cards.set(2, "2♠");
    cards.set(3, "2♠");
    cards.set(4, "2♠");
    cards.set(5, "2♠");
    cards.set(6, "2♠");*/
		//exampleSituation();
	}
	public void setCard(int index, String card){
    cards.set(index, card);
	}
	public void setChipsOnTable(int amountOfChips){
	  this.chipsOnTable = amountOfChips;
	}
	 public int getChipsOnTable(){
	    return chipsOnTable;
	  }
	//public void addPlayer(){	
	//}
	public void exampleSituation(){
		players.add(new ClientPlayer("user1",100));
		players.add(new ClientPlayer("user2",200));
		players.add(new ClientPlayer("xxx",466));
		players.add(new ClientPlayer("user1",100));
		players.add(new ClientPlayer("user2",200));
		players.add(new ClientPlayer("xxx",466));
		players.add(new ClientPlayer("user1",100));
		players.add(new ClientPlayer("user2",200));
		players.add(new ClientPlayer("xxx",466));
		players.add(new ClientPlayer("xxx",466));	}
	
	//public void changeSituation(Message message){
	//
	//}
}
