package myClientServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client {

    private ObjectInputStream in;
    private ObjectOutputStream out;
    private JFrame frame;
    private JTextField dataField;
    private JTextArea messageArea;
    
    public Client(){
        frame = new JFrame("Capitalize Client");
        dataField = new JTextField(40);
        messageArea = new JTextArea(8, 60);
        messageArea.setEditable(false);
        frame.getContentPane().add(dataField, "North");
        frame.getContentPane().add(new JScrollPane(messageArea), "Center");
    }
    
    public static void main(String[] args) throws Exception{
    	Client client = new Client();
        client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.frame.pack();
        client.frame.setVisible(true);
        client.connectToServer();
    }
    public void connectToServer() throws IOException {

        // Get the server address from a dialog box.
        String serverAddress = JOptionPane.showInputDialog(
            frame,
            "Enter IP Address of the Server:",
            "Welcome to the Capitalization Program",
            JOptionPane.QUESTION_MESSAGE);

        // Make connection and initialize streams
        Socket socket = new Socket(serverAddress, 9898);
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());

        // Consume the initial welcoming messages from the server
        Message message;
		try {
			message = (Message)in.readObject();
	        messageArea.append(message.getMessage() + "\n");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        messageArea.append("test \n");
		try {
			message = (Message)in.readObject();
	        messageArea.append(message.getMessage() + "\n");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        messageArea.append("test2 \n");
		socket.close();
    }
}
