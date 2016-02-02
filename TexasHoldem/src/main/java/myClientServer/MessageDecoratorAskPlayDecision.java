package myClientServer;

public class MessageDecoratorAskPlayDecision extends MessageDecorator {
	public MessageDecoratorAskPlayDecision(MessageInterface message){
		super(message);
	}
	public void affectClient(){
		super.affectClient();
		client.askAboutPlay();
		//System.out.println("decorator pytanie o gre");
	}
}
