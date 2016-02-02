package exceptions;

public class NotPositiveAmountException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotPositiveAmountException() {
		super("Amount must be a positive number.");
	}

}
