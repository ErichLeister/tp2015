package exceptions;

public class NotEnoughChipsException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotEnoughChipsException() {
		super("Jest za malo zetonow");
	}

}
