package adp.realmng.dao;

import adp.realmng.model.GAEntityView;
import adp.realmng.model.GATransportRecordView;

public interface GATransportMUDInterface {
	
	// Insert a record for the transport activity of the product from the building site to the supplier
	public String insertTransportRecord(GATransportRecordView gaTransportRecord);
		
	// Insert a record for one of the entity of the transport activity
	public String insertTransportEntity(GAEntityView gaTransportEntity);
}
