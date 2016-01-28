package myClientServer;

import java.io.Serializable;

public class Answer implements Serializable {
	private String answer;
	public Answer(){
		answer = "test-Serializable-test";
	}
	public Answer(String answer){
		this.answer = answer;
	}
	public String getMessage(){
		return answer;
	}
}
