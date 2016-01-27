package myClientServer;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.Socket;
public class UserThread extends Thread{
	private ObjectInput in;
	private Socket socket;
	private Answer answer;
	private RealUser user;
	
	UserThread(ObjectInput in, RealUser user, Socket socket){
		this.user = user;
		this.in = in;
		this.socket = socket;
	}
	
	public void run(){
		try{
			try {
				while (true) {
					synchronized(this){
						System.out.println("reading");
					Answer a = (Answer) in.readObject();
					System.out.println(a.getMessage());
					answer = a;
					user.setAnswer(answer);

					}
					synchronized(user){
						user.notifyAll();
					}
				}
			} 
			catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
        catch (IOException ex) {
            System.out.println("Error handling client");
        } finally {
            try {
            	//if(socket != null)
            	//{
            		socket.close();
            	//}
            } catch (IOException e) {
            	System.out.println("Couldn't close a socket, what's going on?");
            }
            System.out.println("Connection with client closed");
        }
	}
}
