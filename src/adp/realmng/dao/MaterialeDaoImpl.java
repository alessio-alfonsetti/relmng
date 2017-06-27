package adp.realmng.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.InvalidPropertiesFormatException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import adp.realmng.constants.Constants;
import adp.realmng.model.Invoice;
import adp.realmng.model.Materiale;
import adp.realmng.report.StandardInvoiceReport;
import adp.realmng.utilities.FileUtilities;

public class MaterialeDaoImpl implements MaterialeInterface{

	//private DataSource dataSource;
	SimpleJdbcTemplate template;
	 
	/**
	 * config file where are stored all sql statements
	 */
	private static final FileUtilities CONF = 	
			FileUtilities.factoryConfigFileFromPackageResource(MaterialeDaoImpl.class+"", "/resources/sql-queries.xml");
	 
	/**
	 * 	Standard Constructor for Spring Context Initialization
	 */
	public MaterialeDaoImpl(SimpleJdbcTemplate template) {
		this.template = template;
	}
	
	@Override
	public String insert(Materiale materiale) throws InvalidPropertiesFormatException, FileNotFoundException, IOException {

		String sql = CONF.getPropertyString("materiale.insert");
		System.out.println("sql: "+sql);

		String uuid = UUID.randomUUID().toString();
		System.out.println("uuid: "+uuid);
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
	    parameters.addValue("uuid", uuid);
	    parameters.addValue("nome", materiale.getNome());
	    parameters.addValue("numero", materiale.getNumero());
	    parameters.addValue("id_categoria", materiale.getId_categoria());
	    parameters.addValue("nota", materiale.getNota());
		
	    int inserted = template.update(sql, parameters);
		
		System.out.println("invoice inserted: "+inserted);
		
		return uuid;
	}

	@Override
	public List<Map<String, Object>> list()
			throws InvalidPropertiesFormatException, FileNotFoundException,
			IOException {

		String sql = CONF.getPropertyString("invoices.select_all_by_data_emissione");
		
		System.out.println("sql: "+sql);
		
	    List<Map<String,Object>> invoices = template.queryForList(sql);
		
		System.out.println("list of invoices found: "+invoices);
		
		return invoices;
	}

	@Override
	public void delete(String invoiceUuid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modify(Materiale materiale) {
		// TODO Auto-generated method stub
		
	}
	
}
