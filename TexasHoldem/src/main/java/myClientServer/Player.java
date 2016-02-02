package myClientServer;

import myClientServer.Answer;
import myClientServer.Message;
import myClientServer.RealUser;

public class Player {
	RealUser user;
	public String a = "example user message";
	public Player(RealUser user){
		this.user = user;
	}
	public void sendMessage(){
		user.giveMessage(new Message(a));
	}
	public Answer getAnswer(){
		return user.getAnswer(10);
	}
}
