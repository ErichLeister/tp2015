package myClientServer;

public class MessageDecoratorSetBet extends MessageDecorator{
	
	int indexOfPlayer;
	int bet;
	
	public MessageDecoratorSetBet(int indexOfPlayer, int bet, MessageInterface message){
		super(message);
		this.indexOfPlayer = indexOfPlayer;
		this.bet = bet;
	}
	public void affectClient(){
		super.affectClient();
		situation.players.get(indexOfPlayer).setBet(bet);
	}

}
