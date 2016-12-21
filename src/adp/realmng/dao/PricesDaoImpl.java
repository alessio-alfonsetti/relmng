package adp.realmng.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.InvalidPropertiesFormatException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import adp.realmng.constants.Constants;
import adp.realmng.model.Prices;
import adp.realmng.utilities.CommonUtilities;
import adp.realmng.utilities.FileUtilities;

public class PricesDaoImpl implements PricesInterface{

	SimpleJdbcTemplate template;
	
	/**
	 * config file where are stored all sql statements
	 */
	private static final FileUtilities CONF = 	
			FileUtilities.factoryConfigFileFromPackageResource(PricesDaoImpl.class+"", "/resources/sql-queries.xml");
	
	/**
	 * 	Standard Constructor for Spring Context Initialization
	 */
	public PricesDaoImpl(SimpleJdbcTemplate template) {
		this.template = template;
	}

	@Override
	public String insert(Prices prices) throws Exception, FileNotFoundException, IOException {

		String sql = CONF.getPropertyString("prices.insert");

		String uuid = CommonUtilities.generateUuid();

		java.util.Date date = new java.util.Date();
		prices.setLast_update(new Timestamp(date.getTime()));
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
	    parameters.addValue("uuid", uuid);
	    parameters.addValue("uuid_cliente", prices.getUuid_cliente());
	    parameters.addValue("cer", prices.getCer());
	    parameters.addValue("cer_descr", prices.getCer_descr());
	    parameters.addValue("imponibile", prices.getImponibile());
	    parameters.addValue("iva", prices.getIva());
	    parameters.addValue("totale", prices.getTotale());
	    parameters.addValue("last_update", date);
	    parameters.addValue("nota_update", Constants.Price_DESCR_PRIMA_CREAZIONE);
	    
	    int inserted = template.update(sql, parameters);
		System.out.println("price inserted successfully: "+inserted);
		
		return uuid;
	}
	
	@Override
	public List<Map<String, Object>> findPricesByClientId(String clientUUID) throws InvalidPropertiesFormatException, FileNotFoundException, IOException {
		
		String sql = CONF.getPropertyString("prices.select_by_uuid");
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
	    parameters.addValue("uuid", clientUUID);
	    
	    List<Map<String,Object>> prices = template.queryForList(sql, parameters);
		
	    Iterator iterPrices = prices.iterator();
	    
	    Prices pricesObj = new Prices();
	    
	    while (iterPrices.hasNext()) {
	    	
	    	Map<String, Object> obj = (Map<String, Object>) iterPrices.next();
	    	
	    	Set keys = obj.keySet();
			Iterator ikeys = keys.iterator();
			
			System.out.println("price obj: "+obj);
			
			while(ikeys.hasNext())
			{
				String key = (String) ikeys.next();
				System.out.println("customer key: "+key);
				Object value = (Object) obj.get(key);
				System.out.println("customer value: "+value);
				
				if(key.equals(Constants.Price_List_ID)) 
					pricesObj.setId((Integer)value);
				if(key.equals(Constants.Price_List_UUID))
					pricesObj.setUuid((String)value);
				if(key.equals(Constants.Price_List_UUID_CLIENTE))
					pricesObj.setUuid((String)value);
				if(key.equals(Constants.Price_List_CER))
					pricesObj.setUuid((String)value);
				if(key.equals(Constants.Price_List_CER_DESCR))
					pricesObj.setUuid((String)value);
				if(key.equals(Constants.Price_List_IMPONIBILE))
					pricesObj.setUuid((String)value);
				if(key.equals(Constants.Price_List_IVA))
					pricesObj.setUuid((String)value);
				if(key.equals(Constants.Price_List_TOTALE))
					pricesObj.setUuid((String)value);
			}
	    	
	    }
	    	
		return null;
	}
	
}
