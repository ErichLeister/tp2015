package exceptions;

public class WrongColorException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WrongColorException() {
		super("Z�y kolor. Karta mo�e mie� nast�puj�ce kolory: kier, pik, karo, trefl");
	}
}