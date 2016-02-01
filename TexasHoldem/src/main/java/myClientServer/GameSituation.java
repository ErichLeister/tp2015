package myClientServer;

import java.util.ArrayList;

public class GameSituation {
	ArrayList <ClientPlayer> players;
	String cardA;
	String cardB;
	int yourCash;
	
	public GameSituation(){
		players = new ArrayList<ClientPlayer>();
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
