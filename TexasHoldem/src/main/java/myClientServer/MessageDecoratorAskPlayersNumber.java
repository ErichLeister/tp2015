package myClientServer;

public class MessageDecoratorAskPlayersNumber extends MessageDecorator {
	public MessageDecoratorAskPlayersNumber(MessageInterface message){
		super(message);
	}
	public void affectClient(){
		super.affectClient();
		client.askQusetion();
		System.out.println("decorator tutaj powinno byc zapytanie");
	}
}
