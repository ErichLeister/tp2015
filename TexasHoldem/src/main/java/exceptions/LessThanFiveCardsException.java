package exceptions;

public class LessThanFiveCardsException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LessThanFiveCardsException() {
		super("Jest mniej ni¿ 5 kart");
	}


}
