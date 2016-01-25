package myClientServer;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MyServer {

	private List<RealUser> users;
	private ServerThread serverThread;
	private WaitingForGameStart waiting;
	
	public MyServer(){
		users = new ArrayList<RealUser>();
		serverThread = new ServerThread(this);
		waiting = new WaitingForGameStart();
	}
	private boolean shouldGameStart(){
		boolean should = false;
		if(users.size()>=4)
			should = true;
		return should;
	}
	public void giveMessageToAllUsers(Message message){
		for(RealUser user : users){
			user.giveMessage(message);
		}
	}
    public static void main(String[] args){
    	MyServer server = new MyServer();
		server.serverThread.start();
		server.waiting.waitForGameStart();
		server.giveMessageToAllUsers(new Message("PoczatekGry"));
    }
    public void addUser(ObjectInputStream in, ObjectOutputStream out){
    	//System.out.println("testU");
    	users.add(new RealUser(in, out, this));
    }
    
    public void tryStartGame(){
    	if(shouldGameStart()==true)
    	waiting.startGame();
    }
    
    public void startGame(){
    	waiting.startGame();
    }
}
