package myClientServer;

public class MessageDecoratorAskPlayerBet extends MessageDecorator {
	public MessageDecoratorAskPlayerBet(MessageInterface message){
		super(message);
	}
	public void affectClient(){
		super.affectClient();
		client.askAboutBet();
		//System.out.println("decorator tutaj powinno byc zapytanie");
	}
}
