package myClientServer;

import java.io.Serializable;

public abstract class MessageDecorator implements MessageInterface {

	protected Client client;
	protected GameSituation situation;
	protected MessageInterface message;
	
	protected MessageDecorator(MessageInterface message){
		this.message = message;
		client = message.getClient();
	}
	
	public void affectClient(){
		situation = client.getGameSituation();
		//System.out.println(situation.players.size());
		message.affectClient();
	}
	
	public void setClient(Client client){
		this.client = client;
		message.setClient(client);
		//situation = client.getGameSituation();
		//System.out.println(situation.yourCash);
    	//System.out.print(situation.yourCash);
	}
	public String getMessage(){
		return message.getMessage();
	}
	public Client getClient(){
		return message.getClient();
	}

}
