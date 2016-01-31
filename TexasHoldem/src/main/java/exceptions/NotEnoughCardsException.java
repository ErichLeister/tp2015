package exceptions;

public class NotEnoughCardsException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotEnoughCardsException() {
		super("Not enough cards.");
	}
}