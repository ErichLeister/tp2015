package myClientServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RealUser {
	private String nick;
	private UserThread userThread;
	private MyServer server;
	private ObjectOutputStream out;
	private Answer answer;
	RealUser(Socket socket, MyServer server){
		nick = "no name";
		this.server = server;
    	
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			this.out = out;
	        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			this.userThread = new UserThread(in, this, socket);
	    	userThread.start();
			giveMessage(new Message());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	private Answer checkAnswear(Answer answear){
		return answear;
	}
	public Answer getAnswer(){
		setAnswer(new Answer());
		try {
			synchronized(this){
				wait();
				return answer;
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return answer;
		//Answer answer =  userThread.getAnswear();
		
	}
	public synchronized void setAnswer(Answer answer){
		this.answer = answer;
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
