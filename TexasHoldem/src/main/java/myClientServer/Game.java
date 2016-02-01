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
		players.get(0).a = "player 1";
		//players.get(1).a = "player 2";
		//while(1==1)
		//{
			for(Player player : players){
				player.sendMessage();
			}
			players.get(0).getAnswer();
		//}
	}
}
