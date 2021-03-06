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
import adp.realmng.report.StandardInvoiceReport;
import adp.realmng.utilities.FileUtilities;

public class InvoiceDaoImpl implements InvoiceInterface{

	//private DataSource dataSource;
	SimpleJdbcTemplate template;
	 
	/**
	 * config file where are stored all sql statements
	 */
	private static final FileUtilities CONF = 	
			FileUtilities.factoryConfigFileFromPackageResource(InvoiceDaoImpl.class+"", "/resources/sql-queries.xml");
	 
	/**
	 * 	Standard Constructor for Spring Context Initialization
	 */
	public InvoiceDaoImpl(SimpleJdbcTemplate template) {
		this.template = template;
	}
	
	@Override
	public String insert(Invoice invoice) throws Exception,
			FileNotFoundException, IOException {

		String sql = CONF.getPropertyString("invoices.insert");
		System.out.println("sql: "+sql);

		String uuid = UUID.randomUUID().toString();
		System.out.println("uuid: "+uuid);
		
		java.util.Date date= new java.util.Date();
		System.out.println("invoice timestamp: "+new Timestamp(date.getTime()));
		invoice.setData_emissione(new Timestamp(date.getTime()));
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
	    parameters.addValue("uuid", uuid);
	    parameters.addValue("partita_iva", invoice.getPartita_iva());
	    parameters.addValue("id_utente", invoice.getId_utente());
	    parameters.addValue("data_emissione", invoice.getData_emissione());
	    parameters.addValue("descrizione", invoice.getDescrizione());
	    parameters.addValue("importo", invoice.getImporto());
	    parameters.addValue("iva", invoice.getIva());
	    parameters.addValue("importo_totale", invoice.getImporto_totale());
	    parameters.addValue("stato_pagamento", Constants.Invoice_stato_pagamento);
	    parameters.addValue("nome_cantiere", invoice.getNome_cantiere());
		
	    int inserted = template.update(sql, parameters);
		
		System.out.println("invoice inserted: "+inserted);
		
		return uuid;
	}

	/**
	 * Ricerca una fattura per fattura.uuid
	 * 
	 * @param String uuid - lo uuid della fattura da ricercare
	 * @return La fattura stessa
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws InvalidPropertiesFormatException 
	 */
	@Override
	public Invoice findByInvoiceUuid(String invoiceUuid) throws InvalidPropertiesFormatException, FileNotFoundException, IOException {

		String sql = CONF.getPropertyString("invoices.select_by_invoice_uuid");
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
	    parameters.addValue("uuid", invoiceUuid);		
		
		List<Map<String,Object>> invoices = template.queryForList(sql, parameters);
		
		System.out.println("list of invoices found: "+invoices);
		
		Iterator<Map<String, Object>> iter = invoices.iterator();
		Invoice returninvoice = new Invoice();;
		
		while (iter.hasNext())
		{
			Map<String, Object> tmpobj = iter.next();
			Set<String> keys = tmpobj.keySet();
			Iterator<String> iterkeys = keys.iterator();
			while (iterkeys.hasNext()) {
				String key = iterkeys.next();
				if (key.equals(Constants.Invoice_ID))
					returninvoice.setId((Integer) tmpobj.get(key));
				if (key.equals(Constants.Invoice_UUID))
					returninvoice.setUuid((String) tmpobj.get(key));
				if (key.equals(Constants.Invoice_PARTITA_IVA))
					returninvoice.setPartita_iva((String) tmpobj.get(key));
				if (key.equals(Constants.Invoice_ID_UTENTE))
					returninvoice.setId_utente((Integer) tmpobj.get(key));
				if (key.equals(Constants.Invoice_DATA_EMISSIONE))
					returninvoice.setData_emissione((Date) tmpobj.get(key));
				if (key.equals(Constants.Invoice_DESCRIZIONE))
					returninvoice.setDescrizione((String) tmpobj.get(key));
				if (key.equals(Constants.Invoice_IMPORTO))
					returninvoice.setImporto((String) tmpobj.get(key));
				if (key.equals(Constants.Invoice_IVA))
					returninvoice.setIva((String) tmpobj.get(key));
				if (key.equals(Constants.Invoice_IMPORTO_TOTALE))
					returninvoice.setImporto_totale((String) tmpobj.get(key));
				if (key.equals(Constants.Invoice_STATO_PAGAMENTO))
					returninvoice.setStato_pagamento((String) tmpobj.get(key));
				if (key.equals(Constants.Invoice_NOME_CANTIERE))
					returninvoice.setNome_cantiere((String) tmpobj.get(key));
			}
			
		}
		
		System.out.println("returninvoice: "+returninvoice);
		
		return returninvoice;
	}

	@Override
	public List<Map<String, Object>> listAllInvoices()
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
	public void modify(Invoice invoice) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Map<String, Object>> listInvoicesForReport()
			throws InvalidPropertiesFormatException, FileNotFoundException,
			IOException {
		
		String sql = CONF.getPropertyString("invoices.select_for_report");
		
		System.out.println("sql: "+sql);
		
	    List<Map<String,Object>> invoices = template.queryForList(sql);
		
		System.out.println("list of invoices found: "+invoices);
		
		return invoices;
	}

	@Override
	public void generateReport(Invoice invoice) {
		StandardInvoiceReport generatoreReport = new StandardInvoiceReport();
		generatoreReport.createStandardReport(invoice);
	}

	/**
	 * Vengono ricercate le 10 Fatture emesse pi� recentemente.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws InvalidPropertiesFormatException 
	 * 
	 */
	@Override
	public List<Map<String,Object>> findLatestTenInvoices() throws InvalidPropertiesFormatException, FileNotFoundException, IOException {

		String sql = CONF.getPropertyString("invoices.select_latest_ten");
		
		System.out.println("sql: "+sql);
		
		List<Map<String,Object>> invoices = template.queryForList(sql);
		
		System.out.println("list of invoices found: "+invoices);
		
		return invoices;
	}

	/**
	 * Vengono ricercate le Fatture emesse per un cliente.
	 * 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws InvalidPropertiesFormatException 
	 * 
	 */
	@Override
	public List<Map<String,Object>> findInvoicesByCustomerId(int clientId) throws InvalidPropertiesFormatException, FileNotFoundException, IOException {

		String sql = CONF.getPropertyString("invoices.select_invoices_by_customer_id");
		
		System.out.println("sql: "+sql);
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
	    parameters.addValue("id_utente", clientId);
		
		List<Map<String,Object>> invoices = template.queryForList(sql, parameters);
		
		System.out.println("list of invoices found: "+invoices);
		
		return invoices;
	}
	
	/**
	 * Ricerca di una fattura per id fattura.
	 * 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws InvalidPropertiesFormatException 
	 */
	@Override
	public List<Map<String,Object>> findById(int id) throws InvalidPropertiesFormatException, FileNotFoundException, IOException {
		
		String sql = CONF.getPropertyString("invoices.select_invoice_by_id");
		System.out.println("sql: "+sql);
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
	    parameters.addValue("id", id);
		
	    return (template.queryForList(sql, parameters));
	}
	
	
}
