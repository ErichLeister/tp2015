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
    
    public Client(GUI gui){
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
        this.gui = gui;
    }
    
    static public GUI createGUI(){
    	GUI gui = new GUI();
    	return gui;
    }
    
    public static void main(String[] args) throws Exception{
    	Client client = new Client(Client.createGUI());
        /*client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.frame.pack();
        client.frame.setVisible(true);*/
    	client.connectToServer();
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
        Message message;
        boolean isGameRunning = true;
        while(isGameRunning == true)
        {
			try {
				try {
					message = (Message)in.readObject();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        //messageArea.append(message.getMessage() + "\n");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
