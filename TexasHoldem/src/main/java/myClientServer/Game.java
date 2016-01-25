package myClientServer;

import java.util.ArrayList;
import java.util.List;

public class Game {
	private List<Player> players;

	public Game(List<RealUser> users){
		players = new ArrayList<Player>();
		for(RealUser user : users){
			players.add(new Player(user));
		}
	}
	public void startGame(){
		while(1==1)
		{
			for(Player player : players){
				player.sendMessage();
			}
			players.get(0).getAnswer();
		}
	}
}
