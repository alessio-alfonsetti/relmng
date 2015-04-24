package adp.realmng.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Map;

import adp.realmng.model.Customer;
 
public interface CustomerInterface {
	
	// TODO Manage exception. They are all related to an internal error  
	public String insert(Customer customer) throws Exception, FileNotFoundException, IOException;
	
	public Customer findByCustomerId(int custId);
	
	public Customer findByParatitaIva(String partita_iva) throws InvalidPropertiesFormatException, FileNotFoundException, IOException;
	
	public List<Map<String, Object>> listAllCustomers() throws InvalidPropertiesFormatException, FileNotFoundException, IOException;
	
	public void delete (String name);
	
	public void modify (Customer customer);

	public Customer findByCustomerUuid(String custUuid) throws InvalidPropertiesFormatException, FileNotFoundException, IOException;

}