package adp.realmng.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Map;

import adp.realmng.model.Prices;

public interface PricesInterface {

	public Prices insert(Prices prices) throws Exception, FileNotFoundException, IOException;

	List<Map<String, Object>> findPricesByClientUuid(String uuid)
			throws InvalidPropertiesFormatException, FileNotFoundException, IOException;
	
}