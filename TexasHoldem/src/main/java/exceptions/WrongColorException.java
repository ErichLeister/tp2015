package exceptions;

public class WrongColorException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WrongColorException() {
		super("Z³y kolor. Karta mo¿e mieæ nastêpuj¹ce kolory: kier, pik, karo, trefl");
	}
}