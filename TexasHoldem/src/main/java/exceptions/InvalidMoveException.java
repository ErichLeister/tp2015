package exceptions;

public class InvalidMoveException extends Exception {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public InvalidMoveException() {
      super("Wrong move");
  }
}
