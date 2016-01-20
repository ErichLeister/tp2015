package myClientServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
public class UserThread extends Thread{
	private ObjectInputStream in;
	private Socket socket;
	private Answear answear;
	private RealUser user;
	
	UserThread(ObjectInputStream in, RealUser user){
		this.user = user;
		this.in = in;

	}
	private void readMessage(){

	}

	public Answear getAnswear(){
		synchronized(this){
			return answear;
		}
	}
	public void run(){
		try{
			try {
				while (true) {
					synchronized(this){
					Answear a = (Answear) in.readObject();
					answear = a;
					notifyAll();
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
                socket.close();
            } catch (IOException e) {
            	System.out.println("Couldn't close a socket, what's going on?");
            }
            System.out.println("Connection with client closed");
        }
	}
}
