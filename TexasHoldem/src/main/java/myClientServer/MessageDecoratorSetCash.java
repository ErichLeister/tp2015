package myClientServer;

public class MessageDecoratorSetCash extends MessageDecorator{
	
	int indexOfPlayer;
	int cash;
	
	public MessageDecoratorSetCash(int indexOfPlayer, int cash, MessageInterface message){
		super(message);
		this.indexOfPlayer = indexOfPlayer;
		this.cash = cash;
	}
	public void affectClient(){
		super.affectClient();
		situation.players.get(indexOfPlayer).setCash(cash);
	}

}
