package exceptions;

public class NotSevenCardsException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotSevenCardsException() {
		super("Nie ma siedmiu kart");
	}

}
