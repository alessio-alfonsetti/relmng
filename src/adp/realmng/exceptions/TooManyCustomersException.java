package adp.realmng.exceptions;

public class TooManyCustomersException extends Exception{

	private static final long serialVersionUID = 5730991427522150017L;

	public TooManyCustomersException() {	}
	
	public TooManyCustomersException(String message) {
		super(message);
		
		/*TODO Mandare messaggio informativo dell'accaduto all'operatore*/
	}
	
	public TooManyCustomersException(String message, TooManyCustomersException ex) {
		super(message, ex);
	}
}
