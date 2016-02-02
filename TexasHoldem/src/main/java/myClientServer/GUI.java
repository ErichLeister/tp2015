package myClientServer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUI {
	
    private JFrame frame;
    
    private JTextArea messageArea;
    private JPanel contentPane;
    
    private JLabel picture;
    BufferedImage tablePicture;
    BufferedImage avatarPicture;
    
    Client client;
    
    GameSituation situation;
    
    JTextField dataField;
    
    private ArrayList <Dimension> userPlaces;
    public void askAboutString(String question){
    	messageArea.setText(question);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 1;
        //c.anchor
        c.fill = GridBagConstraints.BOTH;
        frame.add(dataField, c);
        
    	frame.pack();
    	frame.setVisible(true);
        /*GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.fill = GridBagConstraints.BOTH;
        frame.add(dataField, c);
    	
    	frame.pack();
    	frame.setVisible(true);*/
    }

    public void askAboutPlay(String question){/////////////////////////
    	messageArea.setText(question);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        
        
    }
    public void askQuestion(){
    	Object[] colours = {"Blue", "Red", "Black", "White"};

		int n = JOptionPane.showOptionDialog(null,
		    "Which colour do you like?",
		    "Choose a colour",
		    JOptionPane.DEFAULT_OPTION,
		    JOptionPane.QUESTION_MESSAGE,
		    null,
		    colours,
		    colours[0]);
		
		System.out.println("The users likes " + colours[n]);

    }
    
	public GUI(GameSituation situation, Client client){
		
		this.client = client;
		
		this.situation = situation;
		
		userPlaces = new ArrayList<Dimension>();
		userPlaces.add(new Dimension(750, 180));
		userPlaces.add(new Dimension(150, 0));
		userPlaces.add(new Dimension(300, 0));
		userPlaces.add(new Dimension(450, 0));
		userPlaces.add(new Dimension(600, 0));
		userPlaces.add(new Dimension(150, 400));
		userPlaces.add(new Dimension(0, 180));
		userPlaces.add(new Dimension(300, 400));
		userPlaces.add(new Dimension(450, 400));
		userPlaces.add(new Dimension(600, 400));
		
        dataField = new JTextField(40);
        dataField.addActionListener(new ActionListener() ////////////////////////////////////
        {
    	    public void actionPerformed(ActionEvent e) 
    	    {
    	    	String answer = dataField.getText();
    	    	frame.remove(dataField);
    	    	frame.pack();
    	    	frame.setVisible(true);
    	    	
    	    	dataField.setText("");
    	    	messageArea.setText("");
    	    	client.answerStringQuestion(answer);
    	        /*GridBagConstraints c = new GridBagConstraints();
    	        c.gridx = 1;
    	        c.gridy = 1;
    	        c.fill = GridBagConstraints.BOTH;
    	        frame.add(dataField, c);
    	    	
    	    	frame.pack();
    	    	frame.setVisible(true);*/
    	    }
        });
		
        frame = new JFrame("Capitalize Client");
        
        frame.setLayout(new GridBagLayout());
        
        GridBagConstraints c = new GridBagConstraints();
        

        messageArea = new JTextArea(8, 60);
        messageArea.setEditable(false);
        
        contentPane = new JPanel();

		try {
			tablePicture = ImageIO.read(new File("src/main/pokerTable.jpg"));
			avatarPicture = ImageIO.read(new File("src/main/avatar.gif"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        //picture = new JLabel(new ImageIcon(image2));
		firstDraw();
		//draw();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
	}
	public void firstDraw(){
		BufferedImage image2 = new BufferedImage(500,500,BufferedImage.TYPE_INT_ARGB);
		Graphics g = image2.getGraphics();
		
		g.drawImage(tablePicture, 0, 0, null);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 
		g.setColor(Color.RED);
		g.drawString("test", 20, 20);
		picture = new JLabel(new ImageIcon(image2));
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        frame.add(picture, c);
        
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.WEST;
        Font f = new Font(null, Font.ITALIC, 30);
        messageArea.setFont(f);
        messageArea.setText("welcome");
        frame.add(messageArea, c);
        /*c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        JButton a = new JButton("test");
        frame.add(a, c);
        frame.remove(a);*/
        
    	frame.pack();
    	frame.setVisible(true);
	}
	public void draw(){
        frame.remove(picture);
		BufferedImage image2 = new BufferedImage(1000,800,BufferedImage.TYPE_INT_ARGB);
		Graphics g = image2.getGraphics();
		

		g.drawImage(tablePicture, 170, 170, null);
		int a;
		for(a = 0; a < situation.players.size(); a++){
			int x = userPlaces.get(a).width;
			int y = userPlaces.get(a).height;
			g.drawImage(avatarPicture,x, y, null);
			g.setColor(Color.RED);
			g.drawString("name: " + situation.players.get(a).getName(), 5 + x, 20 + y);
			g.drawString("cash: " +  Integer.toString(situation.players.get(a).getCash()), x + 5, y + 40);
			g.drawString("bet: " +  Integer.toString(situation.players.get(a).getBet()), x + 5, y + 60);
			g.drawString("state: " +  situation.players.get(a).getState(), x + 5, y + 80);	
			if(situation.players.get(a).getBigBlind())
			g.drawString("*** Big Blind ***", x + 5, y + 100);	
			
			g.drawRoundRect(0, 0,
                    50,
                    50,
                    100, 100);
			if(situation.cards.get(0) != "" && situation.cards.get(0) != null){
				g.drawRoundRect(0, 0,
	                    50,
	                    50,
	                    100, 100);
			}
		}
		//g.drawImage(avatarPicture,0,0, null);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 

		picture = new JLabel(new ImageIcon(image2));
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;

        frame.add(picture, c);
        
    	frame.pack();
    	frame.setVisible(true);
	}
}
