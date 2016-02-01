package myClientServer;

public class MessageDecoratorAddPlayer extends MessageDecorator {
	ClientPlayer player;
	protected Client client;
	//protected GameSituation situation;
	public MessageDecoratorAddPlayer(ClientPlayer player, MessageInterface message){
		super(message);
		this.player = player;
		//this.client = message.getClient();
		//this.situation = super.situation;
	}
	public void affectClient(){
		super.affectClient();
		//System.out.println(player.bet);
		//System.out.println(situation.players.size());

		situation.players.add(player);
	}

}
