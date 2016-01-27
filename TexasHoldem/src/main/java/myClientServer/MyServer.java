package myClientServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
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
		if(users.size()>=1)
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
    	server.users.get(0).getAnswer(0);
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

		try {
	    	RealUser user = new RealUser(socket,this);
	    	ObjectInput in;
			in = new ObjectInputStream(socket.getInputStream());
			UserThread userThread = new UserThread(in, user, socket);
	    	user.initRealUser(userThread);
	    	users.add(user);
	    	tryStartGame();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
    public void tryStartGame(){
    	if(shouldGameStart()==true)
    	waiting.startGame();
    }
    
    public void startGame(){
    	waiting.startGame();
    }
}
