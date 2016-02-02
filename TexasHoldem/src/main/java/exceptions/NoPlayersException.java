package exceptions;

public class NoPlayersException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoPlayersException() {
		super("Not added players.");
	}

}
