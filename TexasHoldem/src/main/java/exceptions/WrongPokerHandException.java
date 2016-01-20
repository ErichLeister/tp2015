package exceptions;

public class WrongPokerHandException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WrongPokerHandException() {
		super("Talia zawiera za ma³o kart");
	}

}
