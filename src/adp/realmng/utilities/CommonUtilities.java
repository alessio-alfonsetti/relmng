package adp.realmng.utilities;

import java.util.UUID;

public class CommonUtilities {

	/**
	 * A method to generate an immutable universally unique identifier (UUID).
	 * 
	 * @return A UUID represents a 128-bit value
	 */
	static public String generateUuid () {
		return (UUID.randomUUID().toString());
	}
	
}
