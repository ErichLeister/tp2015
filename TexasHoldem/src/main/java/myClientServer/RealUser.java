package myClientServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RealUser {
	private String nick;
	private UserThread userThread;
	private MyServer server;
	private ObjectOutput out;
	private Answer answer;
	private Socket socket;
	RealUser(Socket socket, MyServer server){
		nick = "no name";
		this.server = server;
    	this.socket = socket;
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			this.out = out;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	/*private Answer checkAnswear(Answer answear){
		return answear;
	}*/
	public Answer getAnswer(int seconds){
		setAnswer(new Answer());
		try {
			synchronized(this){
				if (seconds == 0)
				{
					wait();
				}
				else if(seconds > 0)
				{
					wait(seconds * 1000);
				}
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
	public void giveMessage(MessageInterface message){
		try {
			out.writeObject(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void initRealUser(UserThread userThread){
        ObjectInputStream in;
			this.userThread = userThread;
	    	userThread.start();
			giveMessage(new Message());

	}
}
