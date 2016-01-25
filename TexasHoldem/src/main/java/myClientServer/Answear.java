package myClientServer;

import java.io.Serializable;

public class Answear implements Serializable {
	private String answear;
	public Answear(){
		answear = "test-Serializable-test";
	}
	public String getMessage(){
		return answear;
	}
}
