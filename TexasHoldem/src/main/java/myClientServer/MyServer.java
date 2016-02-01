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
	private int minGamers = 1;
	
	public MyServer(){
		users = new ArrayList<RealUser>();
		ServerThread serverThread = new ServerThread(this);
		waiting = new WaitingForGameStart();
		serverThread.start();
	}
	private boolean shouldGameStart(){
		boolean should = false;
		if(users.size()>=minGamers)
			should = true;
		return should;
	}
	public void giveMessageToAllUsers(MessageInterface message){
		for(RealUser user : users){
			user.giveMessage(message);
		}
	}
    public static void main(String[] args){
    	MyServer server = new MyServer();
		server.waiting.waitForGameStart();
		//MessageInterface message = new MessageDecoratorAskPlayersNumber(new Message("decorator message test sended by socket"));
		//server.users.get(0).giveMessage(message);
    	//server.users.get(0).getAnswer(0);
    	int answerInt = -1;
    	do{
    		 answerInt = server.askQuestionInt(0, "Podaj liczbe graczy", 10, 0);
    	}
    	while(answerInt == -1);
    	server.minGamers = 3;
    	if(server.users.size() < server.minGamers)
    	{
    		server.waiting.waitForGameStart();
    	}
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
    public int askQuestionInt(int indexOfUser, String question, int max, int min){
    	MessageInterface message = 
    			new MessageDecoratorAskPlayersNumber(new Message(question));
    	users.get(0).giveMessage(message);
    	int answer = users.get(indexOfUser).getAnswer(0).getMessageInt();
    	if (answer < min || answer > max){
    		answer = -1;
    	}
    	return answer;
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
