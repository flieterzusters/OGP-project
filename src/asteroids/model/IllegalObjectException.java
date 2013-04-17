package asteroids.model;

@SuppressWarnings("serial")

public class IllegalObjectException extends RuntimeException {
  
	public IllegalObjectException(String message) {
    super(message);
  }

  public IllegalObjectException(Throwable nested) {
    super(nested);
  }
}