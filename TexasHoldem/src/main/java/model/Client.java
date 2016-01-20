package model;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
        
public class Client {
    class portListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            }
            	
        
    }
    class adresListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	
        }
        
    }
	class potwierdzenieListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			powitalne.setVisible(false);
			//okno.setAlwaysOnTop(true);
			przygotowanieOkna();
        }
    	
	}
    
    private String adres;
    private int port;
    private JPanel p, decyzje;
    private JFrame okno, powitalne;
    private JLabel lPort, lAdres, lImie, komunikaty;
    private JButton potwierdzenie, bFold, bCall, bRaise, bCheck;
    
    private JTextField tfPort, tfAdres, tfImie, konsola; 
    
    public Client() {
        
        powitalne = new JFrame("Logowanie do serwera");
        powitalne.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        powitalne.setSize(500, 500);
        
        okno = new JFrame("Texas Hold'em");
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setSize(8000, 700);
        okno.setVisible(false);
        
        p = new JPanel();
        
        //bPort = new JButton("port serwera");
        //bAdres = new JButton("Adres serwera");
        lImie = new JLabel("imiê gracza");
        lPort = new JLabel("port");
        lAdres = new JLabel("adres swerewa");
        komunikaty = new JLabel("Witaj w serwerze");
        
        potwierdzenie = new JButton("OK");
        bFold = new JButton("fold");
        bCall = new JButton("call");
        bRaise = new JButton("raise");
        bCheck = new JButton("check");
        
        tfPort = new JTextField(4);
        tfAdres = new JTextField(4);
        tfImie = new JTextField(4);
        konsola = new JTextField(4);
        
        //bPort.addActionListener(new portListener());
        //bAdres.addActionListener(new adresListener());
        potwierdzenie.addActionListener(new potwierdzenieListener());
                
        p.setLayout(new GridLayout(3,2));
        powitalne.setLayout(new GridLayout(2,1));
        
        p.add(lPort);
        p.add(tfPort);
        p.add(lAdres);
        p.add(tfAdres);
        p.add(lImie);
        p.add(tfImie);
        powitalne.add(p);
        powitalne.add(potwierdzenie);
      
        powitalne.setVisible(true);
    }
    private void przygotowanieOkna() {
        int szerokosc=3;
        int dlugosc=3;
        
        okno.setLayout(new GridLayout(szerokosc, dlugosc));       
        okno.setVisible(true);           
    }
    
    
    public static void main(String[] args) {
        Client program = new Client();
    }
}