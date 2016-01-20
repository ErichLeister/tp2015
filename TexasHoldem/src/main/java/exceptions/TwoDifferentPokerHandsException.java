package exceptions;

public class TwoDifferentPokerHandsException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TwoDifferentPokerHandsException() {
		super("Nie ma piêciu kart");
	}

}
