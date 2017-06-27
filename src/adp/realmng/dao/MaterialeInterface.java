package adp.realmng.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Map;

import adp.realmng.model.Materiale;

public interface MaterialeInterface {	
	
	public String insert(Materiale materiale) throws InvalidPropertiesFormatException, FileNotFoundException, IOException;

	public List<Map<String, Object>> list() throws InvalidPropertiesFormatException, FileNotFoundException, IOException;
	
	public void delete (String invoiceUuid);
		
	public void modify (Materiale materiale);
	
}
