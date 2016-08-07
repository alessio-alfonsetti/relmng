package adp.realmng.dao;

import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import adp.realmng.model.GAEntityView;
import adp.realmng.model.GATransportRecordView;
import adp.realmng.utilities.FileUtilities;

public class GATransportMUDDaoImpl implements GATransportMUDInterface{

	SimpleJdbcTemplate template;
	
	/**
	 * config file where are stored all sql statements
	 */
	private static final FileUtilities CONF = 	
			FileUtilities.factoryConfigFileFromPackageResource(GATransportMUDDaoImpl.class+"", "/resources/sql-queries.xml");
	
	/**
	 * 	Standard Constructor for Spring Context Initialization
	 */
	public GATransportMUDDaoImpl(SimpleJdbcTemplate template) {
		this.template = template;
	}
	
	@Override
	public String insertTransportRecord(GATransportRecordView gaTransportRecord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String insertTransportEntity(GAEntityView gaTransportEntity) {
		// TODO Auto-generated method stub
		return null;
	}

}
