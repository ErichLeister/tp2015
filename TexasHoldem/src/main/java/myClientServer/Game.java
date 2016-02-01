package myClientServer;

import java.util.ArrayList;
import java.util.List;


public class Game {
	private List<Player> players;

	public Game(List<RealUser> users){
		players = new ArrayList<Player>();
		int i = 1;
		for(RealUser user : users){
			players.add(new Player(user));
			i++;
		}
	}
	public void startGame(){
		//players.get(0) = "player 1";
		//players.get(1).a = "player 2";
		//while(1==1)
		//{
		MessageInterface message = new Message();
		
		for(Player player : players){
			ClientPlayer clientPlayer = new ClientPlayer("test",1);
			message = new MessageDecoratorAddPlayer(clientPlayer, message);
		}
		
		for(Player player : players){
			player.sendMessage(message);
		}
		//players.get(0).getAnswer();
		//}
	}
}
