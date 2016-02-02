package myClientServer;

import java.util.ArrayList;

public class GameSituation {
	ArrayList <ClientPlayer> players;
	ArrayList <String> cards;
	String commonCardA;
	String commonCardB;
	String commonCardC;
	String commonCardD;
	String commonCardE;
	String cardA;
	String cardB;
	int yourCash;
	
	public GameSituation(){
		players = new ArrayList<ClientPlayer>();
		cards = new ArrayList<String>();
		int a;
		for(a = 0; a<7; a++){
			cards.add("");
		}
		cards.set(0, "A♠");
		//exampleSituation();
	}
	public void addPlayer(){
		
	}
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
