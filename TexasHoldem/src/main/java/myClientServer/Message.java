package myClientServer;

import java.io.Serializable;

public class Message implements Serializable {
	private String message;

	public Message(){
		message = "test-Serializable-test";
	}
	public Message(String string){
		message = string;
	}
	public String getMessage(){
		return message;
	}
}
