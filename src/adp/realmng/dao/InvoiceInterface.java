package adp.realmng.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Map;

import adp.realmng.model.Invoice;

public interface InvoiceInterface {
	
	// TODO Manage exception. They are all related to an internal error  
	public String insert(Invoice invoice) throws Exception, FileNotFoundException, IOException;
		
	public Invoice findByInvoiceUuid(String invoiceUuid) throws InvalidPropertiesFormatException, FileNotFoundException, IOException;
	
	public List<Map<String, Object>> findLatestTenInvoices() throws InvalidPropertiesFormatException, FileNotFoundException, IOException;
	
	public List<Map<String, Object>> listAllInvoices() throws InvalidPropertiesFormatException, FileNotFoundException, IOException;
	
	public List<Map<String, Object>> listInvoicesForReport() throws InvalidPropertiesFormatException, FileNotFoundException, IOException;
	
	public void delete (String invoiceUuid);
		
	public void modify (Invoice invoice);
	
	public void generateReport(Invoice invoice);

	public List<Map<String, Object>> findById(int id) throws InvalidPropertiesFormatException, FileNotFoundException, IOException;

	public List<Map<String, Object>> findInvoicesByCustomerId(int clientId) throws InvalidPropertiesFormatException, FileNotFoundException, IOException;
	
}
