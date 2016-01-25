package myClientServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RealUser {
	private UserThread userThread;
	private MyServer myServer;
	private ObjectOutputStream out;
	RealUser(ObjectInputStream in, ObjectOutputStream out, MyServer myServer){
		
		this.out = out;
		this.userThread = new UserThread(in, this);
		this.myServer = myServer;
    	userThread.start();
    	System.out.println("test");
		giveMessage(new Message());

	}
	private Answear checkAnswear(Answear answear){
		return answear;
	}
	public Answear getAnswear(){
		try {
			userThread.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Answear answear =  userThread.getAnswear();
		return answear;
	}
	public void receiveAnswear(Answear answear){
		
	}
	public void giveMessage(Message message){
		try {
			out.writeObject(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
