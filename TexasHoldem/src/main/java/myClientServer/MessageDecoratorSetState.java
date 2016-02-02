package myClientServer;

public class MessageDecoratorSetState extends MessageDecorator{
	
	int indexOfPlayer;
	String state;
	
	public MessageDecoratorSetState (int indexOfPlayer, String state, MessageInterface message){
		super(message);
		this.indexOfPlayer = indexOfPlayer;
		this.state = state;
	}
	public void affectClient(){
		super.affectClient();
		situation.players.get(indexOfPlayer).setState(state);
	}

}
