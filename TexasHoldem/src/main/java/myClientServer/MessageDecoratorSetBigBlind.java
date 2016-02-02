package myClientServer;

public class MessageDecoratorSetBigBlind  extends MessageDecorator{
	
	int indexOfPlayer;
	boolean bigBlind;
	
	public MessageDecoratorSetBigBlind (int indexOfPlayer, boolean bigBlind, MessageInterface message){
		super(message);
		this.indexOfPlayer = indexOfPlayer;
		this.bigBlind = bigBlind;
	}
	public void affectClient(){
		super.affectClient();
		situation.players.get(indexOfPlayer).setBigBlind(bigBlind);
	}

}
