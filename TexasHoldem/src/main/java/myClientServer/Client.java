package myClientServer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client {

    private ObjectInputStream in;
    private ObjectOutputStream out;
    
    private GUI gui;
    private GameSituation situation;
    
    int decisionToMake;
    
    public Client(){
        /*frame = new JFrame("Capitalize Client");
        dataField = new JTextField(40);
        dataField.addActionListener(new ActionListener() 
        {
    	    public void actionPerformed(ActionEvent e) 
    	    {
    	        //out.println(dataField.getText());
    	        try {
    				out.writeObject(new Answer("klient"));
    			} catch (IOException e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    			}
    	    }
        });
        messageArea = new JTextArea(8, 60);
        messageArea.setEditable(false);
        
        contentPane = new JPanel();
        
        frame.getContentPane().add(dataField, "North");
        frame.getContentPane().add(new JScrollPane(messageArea), "West");
        frame.getContentPane().add(contentPane, "East");*/
    	situation = new GameSituation();
        gui = new GUI(situation, this);
    }
    public void answerStringQuestion(String answer){
    	if(decisionToMake == 1){
	    	int a = Integer.parseInt(answer);
	    	try {
				out.writeObject(new Answer("Ile graczy",a));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	    else if(decisionToMake == 2){
	    	try {
				out.writeObject(new Answer("Jaka decyzja",answer));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
	    else if(decisionToMake == 3){
	    	int a = Integer.parseInt(answer);
	    	try {
				out.writeObject(new Answer("Jaki zaklad",a));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    	/*try {
    		synchronized(this)
    		{
			wait(5000);
    		}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    	
       	//askAboutBet();
    }
    public GameSituation getGameSituation(){
    	return situation;
    }
    public void draw(){
    	gui.draw();
    }
    public void askAboutNumberOfPlayers()
    {
    	decisionToMake = 1;
    	gui.askAboutString("Podaj ilosc graczy");
    }
    public void askAboutPlay()
    {
    	decisionToMake = 2;
    	gui.askAboutString("Co teraz robisz?");
    }
    public void askAboutBet()
    {
    	decisionToMake = 3;
    	gui.askAboutString("Podaj kwote zakladu");
    }
    public void askQusetion(){
    	gui.askQuestion();
    	try {
			out.writeObject(new Answer());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public static void main(String[] args) throws Exception{
    	Client client = new Client();
    	//client.askAboutNumberOfPlayers();
    	/*try {
		synchronized(client)
		{
		client.wait(5000);
		}
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	client.askAboutNumberOfPlayers();*/
	
    	//ClientPlayer player = new ClientPlayer("player1",10);
    	//ClientPlayer player2 = new ClientPlayer("player2",120);
    	//GameSituation s = client.getGameSituation();
    	//MessageInterface message = new MessageDecoratorSetBet(0,20,new MessageDecoratorAddPlayer(player,new Message()));
    	//MessageInterface message = new MessageDecoratorAddPlayer(player2,
    	//		new MessageDecoratorAddPlayer(player,new Message()));
    	//MessageInterface message = new MessageDecoratorAddPlayer(player,new MessageDecoratorSetBet(0,0,new Message()));
    	//message.setClient(client);
    	//message.affectClient();
    	//message = new MessageDecoratorSetBet(0,888,new Message());
    	//message.setClient(client);
    	//message.affectClient();
    	client.draw();
    	client.connectToServer();
        /*client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.frame.pack();
        client.frame.setVisible(true);*/

    }
    public void connectToServer() {

        // Get the server address from a dialog box.
        /*String serverAddress = JOptionPane.showInputDialog(
            frame,
            "Enter IP Address of the Server:",
            "Welcome to the Capitalization Program",
            JOptionPane.QUESTION_MESSAGE);*/

        // Make connection and initialize streams
    	String serverAddress = "127.0.0.1";
        Socket socket=null;
		try {
			socket = new Socket(serverAddress, 9898);
	        System.out.print("Polaczenie z serwerem");
	        in = new ObjectInputStream(socket.getInputStream());
	        out = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


        // Consume the initial welcoming messages from the server
        MessageInterface message;
        boolean isGameRunning = true;
        while(isGameRunning == true)
        {
			try {
				try {
					System.out.println("---+++---");
					message = (MessageInterface)in.readObject();
					//message = new MessageDecoratorAskPlayersNumber(new Message());
					System.out.println(message.getMessage());
			    	message.setClient(this);
			    	message.affectClient();
			    	gui.draw();
			    	//gui.askQuestion();
			    	//out.writeObject(new Answer());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return;
				}
		        //messageArea.append(message.getMessage() + "\n");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return;
			}
        }
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
