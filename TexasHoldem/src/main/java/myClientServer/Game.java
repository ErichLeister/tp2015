package myClientServer;

import java.util.ArrayList;
import java.util.List;


public class Game {
	private List<Player> players;

	public Game(List<RealUser> users){
		System.out.println("gras rozpoczeta");
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
		while(1==1){
			for(Player player : players){
				message = new Message();
				message = new MessageDecoratorAskPlayDecision(message);
				player.sendMessage(message);
				int a = player.getPlayDecision();
				System.out.println(a);
				//Answer answer = player.getAnswer();
				//System.out.println(answer.getMessageString());

			}
		}

	}
}
