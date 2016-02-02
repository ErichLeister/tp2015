package myClientServer;

import java.util.ArrayList;
import java.util.List;


public class GameServer {
	private List<PlayerServer> players;

	public GameServer(List<RealUser> users){
		System.out.println("gras rozpoczeta");
		players = new ArrayList<PlayerServer>();
		int i = 1;
		for(RealUser user : users){
			players.add(new PlayerServer(user));
			i++;
		}
	}
	public void startGame(){
		//players.get(0) = "player 1";
		//players.get(1).a = "player 2";
		//while(1==1)
		//{

		MessageInterface message = new Message();
		
		for(PlayerServer player : players){
			ClientPlayer clientPlayer = new ClientPlayer("test",1);
			message = new MessageDecoratorAddPlayer(clientPlayer, message);
		}
		
		for(PlayerServer player : players){
			player.sendMessage(message);
		}
		while(1==1){
			for(PlayerServer player : players){
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
