package adp.realmng.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Map;

import adp.realmng.model.GAEntityView;
import adp.realmng.model.GATransportRecordView;

public interface GATransportMUDInterface {
	
	// Insert a record for the transport activity of the product from the building site to the supplier
	public String insertTransportRecord(GATransportRecordView gaTransportRecord) throws InvalidPropertiesFormatException, FileNotFoundException, IOException, Exception;
		
	// Insert a record for one of the entity of the transport activity
	public String insertTransportEntity(GAEntityView gaTransportEntity);

	List<Map<String, Object>> listAllTransports()
			throws InvalidPropertiesFormatException, FileNotFoundException, IOException;
}
