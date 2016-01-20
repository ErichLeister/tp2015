package exceptions;

public class TooLowDeckSizeException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TooLowDeckSizeException() {
		super("Talia zawiera za ma³o kart");
	}
}