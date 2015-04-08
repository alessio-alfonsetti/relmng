package adp.realmng.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import adp.realmng.model.Invoice;
import adp.realmng.utilities.FileUtilities;

public class InvoiceDaoImpl implements InvoiceInterface{

	//private DataSource dataSource;
	SimpleJdbcTemplate template;
	 
	/**
	 * config file where are stored all sql statements
	 */
	private static final FileUtilities CONF = 	
			FileUtilities.factoryConfigFileFromPackageResource(CustomerDaoImpl.class+"", "/resources/sql-queries.xml");
		
	 
	/**
	 * 	Standard Constructor for Spring Context Initialization
	 */
	public InvoiceDaoImpl(SimpleJdbcTemplate template) {
		this.template = template;
	}
	
	
	@Override
	public void insert(Invoice invoice) throws Exception,
			FileNotFoundException, IOException {
		
		String sql = CONF.getPropertyString("invoices.insert");
		
		System.out.println("sql: "+sql);
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
	    parameters.addValue("uuid", "000000000001");
	    parameters.addValue("partita_iva", "alessio.alfonsetti@gmail.com");
	    parameters.addValue("id_utente", "1");
	    parameters.addValue("data_emissione", "Alessio");
	    parameters.addValue("descrizione", "Alfonsetti");
	    parameters.addValue("importo_totale", "2");
	    parameters.addValue("stato_pagamento", "2");
	    parameters.addValue("nome_cantiere", "test");
		
	    int inserted = template.update(sql, parameters);
		
		System.out.println("invoice inserted: "+inserted);
		
	}

	@Override
	public Invoice findByInvoiceUuid(String invoiceUuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> listAllInvoices()
			throws InvalidPropertiesFormatException, FileNotFoundException,
			IOException {

		String sql = CONF.getPropertyString("invoices.get_all_by_data_emissione");
		
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
	public void modify(Invoice invoice) {
		// TODO Auto-generated method stub
		
	}

}
