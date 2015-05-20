package adp.realmng.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Map;

import adp.realmng.model.Customer;
import adp.realmng.model.Worksite;

public interface WorksiteInterface {
	
	public String insert(Worksite worksite) throws InvalidPropertiesFormatException, FileNotFoundException, IOException;
		
	public Worksite findByWorksiteId(int worksiteId);
		
	public Worksite findByEmployeeId(int worksiteId);
		
	public List<Map<String, Object>> listAllWorksites() throws InvalidPropertiesFormatException, FileNotFoundException, IOException;
		
	public void modify(Worksite worksite);

	public int deleteByWorksiteId(int id);

	public Worksite findByWorksiteUuid(String worksiteUuid);
	
}
