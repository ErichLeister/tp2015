package myClientServer;

import java.util.ArrayList;
import java.util.List;

import model.Player;

public class Game {
	private List<Player> players;

	public Game(List<RealUser> users){
		players = new ArrayList<Player>();
		int i = 1;
		for(RealUser user : users){
			players.add(new Player("player " + i, user));
			i++;
		}
	}
	public void startGame(){
		//players.get(0) = "player 1";
		//players.get(1).a = "player 2";
		while(1==1)
		{
			for(Player player : players){
				player.sendMessage("message");
			}
			players.get(0).getAnswer();
		}
	}
}
