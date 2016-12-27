package adp.realmng.exceptions;

public class NoClientException extends Exception{

	private static final long serialVersionUID = 5730991427522150017L;

	public NoClientException() {	}
	
	public NoClientException(String message) {
		super(message);
		
		/*TODO Mandare messaggio informativo dell'accaduto all'operatore*/
	}
	
	public NoClientException(String message, NoClientException ex) {
		super(message, ex);
	}
	
}
