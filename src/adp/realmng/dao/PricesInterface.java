package adp.realmng.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Map;

import adp.realmng.model.Prices;

public interface PricesInterface {

	public String insert(Prices prices) throws Exception, FileNotFoundException, IOException;

	List<Map<String, Object>> findPricesByClientId(String uuid) throws InvalidPropertiesFormatException, FileNotFoundException, IOException;
	
	//public void modify (Prices prices);

	//public int deleteByUuid(String uuid) throws InvalidPropertiesFormatException, FileNotFoundException, IOException;

}