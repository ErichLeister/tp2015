package myClientServer;

import java.io.Serializable;

public class Answer implements Serializable {
	private String answer;
	private int answerInt;
	private String answerString;
	public Answer(){
		answer = "test-Serializable-test";
		answerString = "";
		answerInt = 0;
	}
	public Answer(String answer){
		this.answer = answer;
		answerString = "";
		answerInt = 0;
	}
	public Answer(String answer, int answerInt, String answerString){
		this.answer = answer;
		this.answerInt = answerInt;
		this.answerString = answerString;
	}	
	public Answer(String answer, String answerString){
		this.answer = answer;
		this.answerInt = 0;
		this.answerString = answerString;
	}
	public Answer(String answer, int answerInt){
		this.answer = answer;
		this.answerInt = answerInt;
		this.answerString = "";
	}	
	public String getMessage(){
		return answer;
	}
	public int getMessageInt(){
		return answerInt;
	}
	public String getMessageString(){
		return answerString;
	}
}
