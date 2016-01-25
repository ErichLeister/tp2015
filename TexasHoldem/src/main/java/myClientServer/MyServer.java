package myClientServer;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MyServer {

	private List<RealUser> users;
	private WaitingForGameStart waiting;
	
	public MyServer(){
		users = new ArrayList<RealUser>();
		ServerThread serverThread = new ServerThread(this);
		waiting = new WaitingForGameStart();
		serverThread.start();
	}
	private boolean shouldGameStart(){
		boolean should = false;
		if(users.size()>=2)
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
		server.waiting.waitForGameStart();
    	/*try {
    		synchronized(server){
			server.wait(10000);
    		}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		Game game = new Game(server.users);
		game.startGame();
		//server.giveMessageToAllUsers(new Message("PoczatekGry"));
    }
    public void addUser(Socket socket){
    	//System.out.println("testU");
    	users.add(new RealUser(socket,this));
    	tryStartGame();
    }
    
    public void tryStartGame(){
    	if(shouldGameStart()==true)
    	waiting.startGame();
    }
    
    public void startGame(){
    	waiting.startGame();
    }
}
