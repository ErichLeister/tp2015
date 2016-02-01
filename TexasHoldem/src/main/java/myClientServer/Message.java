package myClientServer;

import java.io.Serializable;

public class Message implements MessageInterface {
	private String message;
	private Client client;
	private GameSituation situation;

	public Message(){
		message = "test-Serializable-test";
	}
	public Message(String string){
		message = string;
	}
	public String getMessage(){
		return message;
	}
	public void setClient(Client client){
		this.client = client;
		//situation = client.getGameSituation();
		//System.out.println(situation.toString());
		//System.out.print("test");
	}
	public Client getClient(){
		return client;
	}
	public void affectClient(){
		
	}
}
