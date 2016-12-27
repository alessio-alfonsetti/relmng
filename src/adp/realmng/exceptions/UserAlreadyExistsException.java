package adp.realmng.exceptions;

public class UserAlreadyExistsException extends Exception{
	
	private static final long serialVersionUID = 5730991427522150017L;

	public UserAlreadyExistsException() {	}
	
	public UserAlreadyExistsException(String message) {
		super(message);
		
		/*TODO Mandare messaggio informativo dell'accaduto all'operatore*/
	}
	
	public UserAlreadyExistsException(String message, UserAlreadyExistsException ex) {
		super(message, ex);
	}

}
