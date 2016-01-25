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

	        System.out.println("test2");
        	ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
	        System.out.println("test3");
	        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
	        
	        server.addUser(in, out);
		        }
	        } 
        		catch(IOException e)
        		{
        			e.printStackTrace();          			
        		}
	        }

	
}
