package exceptions;

import pokerhand.PokerHand;

public class PokerHandException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PokerHandException(PokerHand ph, String msg) {
		super("B³¹d klasy PokerHand (" + ph.getClass().getName() + "): "+ msg);
	}
	
	public PokerHandException(PokerHand ph) {
		super("B³¹d klasy PokerHand (" + ph.getClass().getName() + ").");
	}

}
