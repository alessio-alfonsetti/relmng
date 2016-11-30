package adp.realmng.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import adp.realmng.model.GAEntityView;
import adp.realmng.model.GATransportRecordView;
import adp.realmng.utilities.CommonUtilities;
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
	public String insertTransportRecord(GATransportRecordView gaTransportRecord) throws InvalidPropertiesFormatException, FileNotFoundException, IOException, Exception {
		
		String sql = CONF.getPropertyString("transport.insert");
		System.out.println("sql: "+sql);
		
		String uuid = CommonUtilities.generateUuid();
		
		System.out.println("uuid: "+uuid);
		System.out.println("codice_materiale: "+gaTransportRecord.getCodice_materiale());
		System.out.println("azienda di destinazione: "+gaTransportRecord.getAzienda_destinazione());
		System.out.println("quantita: "+gaTransportRecord.getQuantita());
		System.out.println("azienda_provenienza: "+gaTransportRecord.getAzienda_provenienza());
		System.out.println("data_inizio: "+gaTransportRecord.getData_inizio());
		System.out.println("ora_inizio: "+gaTransportRecord.getOra_inizio());
		System.out.println("data_fine: "+gaTransportRecord.getData_fine());
		System.out.println("ora_fine: "+gaTransportRecord.getOra_fine());
		System.out.println("nota: "+gaTransportRecord.getNota());
		
		
		//java.util.Date date= new java.util.Date();
		//System.out.println(new Timestamp(date.getTime()));
		
		SimpleDateFormat dayFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateStart = (Date) dayFormat.parse(gaTransportRecord.getData_inizio());
		System.out.println("1: "+dateStart);
		long timeStart = dateStart.getTime();
		System.out.println("2: "+timeStart);
		Timestamp data_inizio_trasporto = new Timestamp(timeStart);
		System.out.println("3: "+data_inizio_trasporto);
		
		Date dateEnd = (Date) dayFormat.parse(gaTransportRecord.getData_fine());
		long timeEnd = dateEnd.getTime();
		Timestamp data_fine_trasporto = new Timestamp(timeEnd);
				
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("uuid", uuid);
		parameters.addValue("idmaterialetrasporto", gaTransportRecord.getCodice_materiale());
	    parameters.addValue("quantita", gaTransportRecord.getQuantita());
	    parameters.addValue("idaziendaproduttrice", gaTransportRecord.getAzienda_provenienza());
	    parameters.addValue("idaziendasmaltitrice", gaTransportRecord.getAzienda_destinazione());
	    parameters.addValue("datainiziotrasporto", data_inizio_trasporto);
	    parameters.addValue("orainiziotrasporto", gaTransportRecord.getOra_inizio());
	    parameters.addValue("datafinetrasporto", data_fine_trasporto);
	    parameters.addValue("orafinetrasporto", gaTransportRecord.getOra_fine());
	    parameters.addValue("note", gaTransportRecord.getNota());
	    
	    System.out.println("Inserisco record di trasporto");
	    
	    int inserted = template.update(sql, parameters);
		
		System.out.println("insert ended: "+inserted);
		
		return uuid;
	}

	@Override
	public String insertTransportEntity(GAEntityView gaTransportEntity) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * This method retrieves all the records from the table transfer, ordered by the newest inserted
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws InvalidPropertiesFormatException 
	 */
	@Override
	public List<Map<String, Object>> listAllTransports () throws InvalidPropertiesFormatException, FileNotFoundException, IOException  {

		String sql = CONF.getPropertyString("transports.get_all_by_id");
		
	    List<Map<String,Object>> customers = template.queryForList(sql);
		
		System.out.println("list of customers found: "+customers);
		
		return customers;
	}

}
