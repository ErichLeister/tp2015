package myClientServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread{
	MyServer server;
	public ServerThread(MyServer server){
		this.server = server;
	}
	public void run(){
		try{
	        ServerSocket listener = new ServerSocket(9898);
	        while(true){
	        Socket socket = listener.accept();


	        
	        server.addUser(socket);
		        }
	        } 
        		catch(IOException e)
        		{
        			e.printStackTrace();          			
        		}
	        }

	
}
