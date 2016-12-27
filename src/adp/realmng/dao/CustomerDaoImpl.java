package adp.realmng.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import adp.realmng.constants.Constants;
import adp.realmng.exceptions.NoClientException;
import adp.realmng.exceptions.TooManyCustomersException;
import adp.realmng.exceptions.UserAlreadyExistsException;
import adp.realmng.model.Customer;
import adp.realmng.utilities.CommonUtilities;
import adp.realmng.utilities.FileUtilities;
 
public class CustomerDaoImpl implements CustomerInterface{

	SimpleJdbcTemplate template;
 
	/**
	 * config file where are stored all sql statements
	 */
	private static final FileUtilities CONF = 	
			FileUtilities.factoryConfigFileFromPackageResource(CustomerDaoImpl.class+"", "/resources/sql-queries.xml");
	
	/**
	 * 	Standard Constructor for Spring Context Initialization
	 */
	public CustomerDaoImpl(SimpleJdbcTemplate template) {
		this.template = template;
	}
	
	@Override
	public String insert(Customer customer) throws Exception, FileNotFoundException, IOException{
 
		//TODO Add check to make sure that at least basic information are available; otherwise the insert doesnt have to happen		
		
		String sql = CONF.getPropertyString("customers.insert");
		System.out.println("sql: "+sql);
		
		String uuid = CommonUtilities.generateUuid();
		
		java.util.Date date= new java.util.Date();
		System.out.println(new Timestamp(date.getTime()));
		customer.setData_inserimento(new Timestamp(date.getTime()));
		
		String username = (customer.getFirstname()+customer.getLastname()).toLowerCase();
		if(username.equals(null) || username.length()==0 || username.equals(""))
			username = customer.getRagione_sociale().toLowerCase();
		if(username.equals(null) || username.length()==0 || username.equals(""))
			username = uuid.toLowerCase();
		username = username.replace(" ", "_");
		
		System.out.println("id: "+customer.getId());
		System.out.println("uuid: "+uuid);
		System.out.println("ragione_sociale: "+customer.getRagione_sociale());
		System.out.println("nome: "+customer.getFirstname());
		System.out.println("cognome: "+customer.getLastname());
		System.out.println("username: "+username);
		System.out.println("email: "+customer.getEmail());
		System.out.println("partita_iva: "+customer.getPartita_iva());
		System.out.println("codice_fiscale: "+customer.getCodice_fiscale());
		System.out.println("nota: "+customer.getNota());
		System.out.println("numero_cellulare: "+customer.getNumero_cellulare());
		System.out.println("data_inserimento: "+customer.getData_inserimento());
		System.out.println("notifica_creazione_incompleta: "+customer.isNotifica_creazione_incompleta());
		System.out.println("iban: "+customer.getIban());
		System.out.println("indirizzo: "+customer.getIndirizzo());
		System.out.println("id_ruolo"+ customer.getId_ruolo());
		
		// Check if user already exists checking Ragione Sociale and Surname
		MapSqlParameterSource parametersFind = new MapSqlParameterSource();
		parametersFind.addValue("codice_fiscale", customer.getCodice_fiscale());
		parametersFind.addValue("ragione_sociale", customer.getRagione_sociale());
		String sqlFind = CONF.getPropertyString("customers.select_by_ragSocCogn");
		List<Map<String,Object>> customerExist = template.queryForList(sql, parametersFind);
		if(customerExist.size()!=0)
			throw new UserAlreadyExistsException();
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
	    parameters.addValue("uuid", uuid);
	    parameters.addValue("username", username);
	    parameters.addValue("firstname", customer.getFirstname());
	    parameters.addValue("lastname", customer.getLastname());
	    parameters.addValue("ragione_sociale", customer.getRagione_sociale());
	    parameters.addValue("email", customer.getEmail());
	    parameters.addValue("partita_iva", customer.getPartita_iva());
	    parameters.addValue("codice_fiscale", customer.getCodice_fiscale());
	    parameters.addValue("nota", customer.getNota());
	    parameters.addValue("numero_cellulare", customer.getNumero_cellulare());
	    parameters.addValue("data_inserimento", customer.getData_inserimento());
	    parameters.addValue("indirizzo", customer.getIndirizzo());
	    parameters.addValue("id_ruolo", customer.getId_ruolo());
	    parameters.addValue("iban", customer.getIban());
	    
	    /**
	     * @notes: notifica_creazione_incompleta é false quando il profilo utente é creato propriamente dall'operatore
	     */
	    parameters.addValue("notifica_creazione_incompleta", false);
	    
	    /**
	     * @notes: enabled é true quando il profilo utente é creato per indicare un cliente attivo. 
	     * Viene posto a false in fase di cancellazione logica (disabilitazione) 
	     */
	    parameters.addValue("enabled", true);
	    
	    //parameters.addValue("password", customer.getPassword());
	    //parameters.addValue("numero_telefono", customer.getNumero_telefono());
	    //parameters.addValue("numero_fax", customer.getNumero_fax());
	    
	    System.out.println("parameters: "+parameters);
		
	    int inserted = template.update(sql, parameters);
		
		System.out.println("insert ended: "+inserted);
		
		return uuid;
	}

	public List<Map<String, Object>> listCustomersForNotification () throws InvalidPropertiesFormatException, FileNotFoundException, IOException
	{
		String sql = CONF.getPropertyString("customers.select_for_notifaction");
		
		System.out.println("sql: "+sql);
		
	    List<Map<String,Object>> customers = template.queryForList(sql);
		
		System.out.println("list of customers found: "+customers);
		
		return customers;
	}
	
	/**
	 * This method retrieves all the customers by surname
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws InvalidPropertiesFormatException 
	 */
	@Override
	public List<Map<String, Object>> listAllCustomers () throws InvalidPropertiesFormatException, FileNotFoundException, IOException  {

		String sql = CONF.getPropertyString("customers.get_all_by_name");
		
		System.out.println("sql: "+sql);
		
	    List<Map<String,Object>> customers = template.queryForList(sql);
		
		System.out.println("list of customers found: "+customers);
		
		return customers;
	}
 
	/**
	 * This method retrieves all the employers ordered by surname
	 * 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws InvalidPropertiesFormatException 
	 */
	@Override
	public List<Map<String, Object>> listAllEmployers () throws InvalidPropertiesFormatException, FileNotFoundException, IOException  {

		String sql = CONF.getPropertyString("employers.get_all_by_lastname");
		
		System.out.println("sql: "+sql);
		
	    List<Map<String,Object>> employers = template.queryForList(sql);
		
		System.out.println("list of employers found: "+employers);
		
		return employers;
	}
	
	public Customer parseCustomer(Map<String, Object> listEntry_)
	{
		Customer customer = new Customer();
		
		while(!listEntry_.isEmpty())
			customer.setUuid((String) listEntry_.get(Constants.Customer_UUID));
					
		return customer;
	}

	@Override
	public int deleteByUuid(String uuid) throws InvalidPropertiesFormatException, FileNotFoundException, IOException {

		String sql = CONF.getPropertyString("customers.deactivate_by_uuid");
		System.out.println("sql: "+sql);
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
	    parameters.addValue("uuid", uuid);
		
	    int res = template.update(sql, parameters);
		
	    return res;
	}

	/**
	 * A method to generate an immutable universally unique identifier (UUID).
	 * 
	 * @return A UUID represents a 128-bit value
	 */
	//private String generateUuid () {
	//	return (UUID.randomUUID().toString());
	//}

	@Override
	public Customer findByCustomerUuid(String uuid) throws InvalidPropertiesFormatException, FileNotFoundException, IOException {

		Customer customer = new Customer();
		
		String sql = CONF.getPropertyString("customers.select_by_uuid");
		
		System.out.println("sql: "+sql);
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
	    parameters.addValue("uuid", uuid);
		
	    List<Map<String,Object>> customers = template.queryForList(sql, parameters);
	    
	    Iterator iterCustomers = customers.iterator();
	    
	    while(iterCustomers.hasNext())
	    {
	    	Map<String, Object> obj = (Map<String, Object>) iterCustomers.next();
	    	
	    	Set keys = obj.keySet();
			Iterator ikeys = keys.iterator();
			
			System.out.println("customer obj: "+obj);
			
			while(ikeys.hasNext())
			{
				String key = (String) ikeys.next();
				System.out.println("customer key: "+key);
				Object value = (Object) obj.get(key);
				System.out.println("customer value: "+value);

				if(key.equals(Constants.Customer_ID)) 
					customer.setId((Integer)value);
				if(key.equals(Constants.Customer_UUID)) 
					customer.setUuid((String)value);
				if(key.equals(Constants.Customer_USERNAME))
					customer.setUsername((String)value);
				if(key.equals(Constants.Customer_FIRSTNAME))
					customer.setFirstname((String)value);
				if(key.equals(Constants.Customer_MIDDLENAME))
					customer.setMiddlename((String)value);
				if(key.equals(Constants.Customer_LASTNAME))
					customer.setLastname((String)value);
				if(key.equals(Constants.Customer_PASSWORD))
					customer.setPassword((String)value);
				if(key.equals(Constants.Customer_RAGIONE_SOCIALE))
					customer.setRagione_sociale((String)value);
				if(key.equals(Constants.Customer_PARTITA_IVA))
					customer.setPartita_iva((String)value);
				if(key.equals(Constants.Customer_CODICE_FISCALE))
					customer.setCodice_fiscale ((String)value);
				if(key.equals(Constants.Customer_INDIRIZZO))
					customer.setIndirizzo((String)value);
				if(key.equals(Constants.Customer_NUMERO_TELEFONO))
					customer.setNumero_telefono((String)value);
				if(key.equals(Constants.Customer_NUMERO_CELLULARE))
					customer.setNumero_cellulare((String)value);
				if(key.equals(Constants.Customer_NUMERO_FAX))
					customer.setNumero_fax((String)value);
				if(key.equals(Constants.Customer_EMAIL))
					customer.setEmail((String)value);
				if(key.equals(Constants.Customer_NOTA))
					customer.setNota((String)value);
				if(key.equals(Constants.Customer_ENABLED))
					customer.setEnabled((Boolean)value);
				if(key.equals(Constants.Customer_IDRUOLO))
					customer.setId_ruolo((Integer)value);
				if(key.equals(Constants.Customer_DATA_INSERIMENTO))
					customer.setData_inserimento((Timestamp)value);
			}
	    }
	    
		return customer;
	}
	
	@Override
	public List<Map<String,Object>> findDeactivatedCustomers() throws InvalidPropertiesFormatException, FileNotFoundException, IOException {
		
		String sql = CONF.getPropertyString("customers.get_all_deactivated");
		
		System.out.println("sql: "+sql);
		
	    List<Map<String,Object>> employers = template.queryForList(sql);
		
		System.out.println("list of employers found: "+employers);
		
		return employers;
		
	}
	
	@Override
	public Map<String, Object> findByCustomerId(int custId) throws InvalidPropertiesFormatException, FileNotFoundException, IOException {
		
		Map<String, Object> customer = new HashMap<String, Object>();
		
		String sql = CONF.getPropertyString("customers.select_by_id");
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", custId);
		
		List<Map<String, Object>> found = template.queryForList(sql, parameters);
		System.out.println("users found on the db: "+found.size());
		Iterator<Map<String, Object>> iter = found.iterator();
		
		int counter = 0;
		while (iter.hasNext())
		{
			Map<String, Object> obj = iter.next();
			System.out.println("customer obj: "+obj);
			//customer = (Customer) obj;
			
			Set keys = obj.keySet();
			Iterator ikeys = keys.iterator();
			
			System.out.println("customer obj: "+obj);
			
			while(ikeys.hasNext())
			{
				String key = (String) ikeys.next();
				System.out.println("customer key: "+key);
				Object value = (Object) obj.get(key);
				System.out.println("customer value: "+value);

				if(key.equals(Constants.Customer_ID)) 
					customer.put(Constants.Customer_ID, value);
				if(key.equals(Constants.Customer_UUID)) 
					customer.put(Constants.Customer_UUID, value);
				if(key.equals(Constants.Customer_USERNAME))
					customer.put(Constants.Customer_USERNAME, value);
				if(key.equals(Constants.Customer_FIRSTNAME))
					customer.put(Constants.Customer_FIRSTNAME, value);
				if(key.equals(Constants.Customer_MIDDLENAME))
					customer.put(Constants.Customer_MIDDLENAME, value);
				if(key.equals(Constants.Customer_LASTNAME))
					customer.put(Constants.Customer_LASTNAME, value);
				if(key.equals(Constants.Customer_PASSWORD))
					customer.put(Constants.Customer_PASSWORD, value);
				if(key.equals(Constants.Customer_RAGIONE_SOCIALE))
					customer.put(Constants.Customer_RAGIONE_SOCIALE, value);
				if(key.equals(Constants.Customer_PARTITA_IVA))
					customer.put(Constants.Customer_PARTITA_IVA, value);
				if(key.equals(Constants.Customer_CODICE_FISCALE))
					customer.put(Constants.Customer_CODICE_FISCALE, value);
				if(key.equals(Constants.Customer_INDIRIZZO))
					customer.put(Constants.Customer_INDIRIZZO, value);
				if(key.equals(Constants.Customer_NUMERO_TELEFONO))
					customer.put(Constants.Customer_NUMERO_TELEFONO, value);
				if(key.equals(Constants.Customer_NUMERO_CELLULARE))
					customer.put(Constants.Customer_NUMERO_CELLULARE, value);
				if(key.equals(Constants.Customer_NUMERO_FAX))
					customer.put(Constants.Customer_NUMERO_FAX, value);
				if(key.equals(Constants.Customer_EMAIL))
					customer.put(Constants.Customer_EMAIL, value);
				if(key.equals(Constants.Customer_NOTA))
					customer.put(Constants.Customer_NOTA, value);
				if(key.equals(Constants.Customer_ENABLED))
					customer.put(Constants.Customer_ENABLED, value);
				if(key.equals(Constants.Customer_IDRUOLO))
					customer.put(Constants.Customer_IDRUOLO, value);
				if(key.equals(Constants.Customer_DATA_INSERIMENTO))
					customer.put(Constants.Customer_DATA_INSERIMENTO, value);
			}
			
			counter ++;
			System.out.println("customer counter: "+counter);
			System.out.println("customer: "+customer);
		}
		
		System.out.println("customer counter: "+counter);
		if(counter > 1) {
			// TODO Ci sono troppi utenti su DB associati alla partita_iva utilizzata
			
			try {

				throw new TooManyCustomersException("Too many customers for one id_utente. Error Code: "+Constants.ERR_TOO_MANY_CUSTOMERS_FOR_ONE_ID_UTENTE);
			
			} catch (TooManyCustomersException e) {
				e.printStackTrace();
			}
		} else if (counter == 0) {
			// TODO Non ci sono utenti su DB con la partita iva inserita
			
		}
		
		return customer;
	}

	@Override
	public void modify(Customer customer) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Metodo per la ricerca di un cliente su DB attraverso il valore della partita_iva.
	 * 
	 * @return Se il customer non viene trovato su DB attraverso la partita_iva, viene restituito il valore null e quindi l'operatore dovrá
	 * inserire a mano i valori sulla fattura.
	 */
	@Override
	public Customer findByPartitaIva(String partita_iva) throws InvalidPropertiesFormatException, FileNotFoundException, IOException {

		Customer customer = new Customer();
		
		String sql = CONF.getPropertyString("customers.select_by_partita_iva");
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("partita_iva", partita_iva);
		
		List<Map<String, Object>> found = template.queryForList(sql, parameters);
		System.out.println("users found on the db: "+found.size());
		Iterator<Map<String, Object>> iter = found.iterator();
		
		int counter = 0;
		while (iter.hasNext())
		{
			Map<String, Object> obj = iter.next();
			System.out.println("customer obj: "+obj);
			//customer = (Customer) obj;
			
			Set keys = obj.keySet();
			Iterator ikeys = keys.iterator();
			
			System.out.println("customer obj: "+obj);
			
			while(ikeys.hasNext())
			{
				String key = (String) ikeys.next();
				System.out.println("customer key: "+key);
				Object value = (Object) obj.get(key);
				System.out.println("customer value: "+value);

				if(key.equals(Constants.Customer_ID)) 
					customer.setId((Integer)value);
				if(key.equals(Constants.Customer_UUID)) 
					customer.setUuid((String)value);
				//System.out.println("customer uuid: "+customer.getUuid());
				if(key.equals(Constants.Customer_USERNAME))
					customer.setUsername((String)value);
				if(key.equals(Constants.Customer_FIRSTNAME))
					customer.setFirstname((String)value);
				if(key.equals(Constants.Customer_MIDDLENAME))
					customer.setMiddlename((String)value);
				if(key.equals(Constants.Customer_LASTNAME))
					customer.setLastname((String)value);
				if(key.equals(Constants.Customer_PASSWORD))
					customer.setPassword((String)value);
				if(key.equals(Constants.Customer_RAGIONE_SOCIALE))
					customer.setRagione_sociale((String)value);
				if(key.equals(Constants.Customer_PARTITA_IVA))
					customer.setPartita_iva((String)value);
				if(key.equals(Constants.Customer_CODICE_FISCALE))
					customer.setCodice_fiscale ((String)value);
				if(key.equals(Constants.Customer_INDIRIZZO))
					customer.setIndirizzo((String)value);
				if(key.equals(Constants.Customer_NUMERO_TELEFONO))
					customer.setNumero_telefono((String)value);
				if(key.equals(Constants.Customer_NUMERO_CELLULARE))
					customer.setNumero_cellulare((String)value);
				if(key.equals(Constants.Customer_NUMERO_FAX))
					customer.setNumero_fax((String)value);
				if(key.equals(Constants.Customer_EMAIL))
					customer.setEmail((String)value);
				if(key.equals(Constants.Customer_NOTA))
					customer.setNota((String)value);
				if(key.equals(Constants.Customer_ENABLED))
					customer.setEnabled((Boolean)value);
				if(key.equals(Constants.Customer_IDRUOLO))
					customer.setId_ruolo((Integer)value);
				if(key.equals(Constants.Customer_DATA_INSERIMENTO))
					customer.setData_inserimento((Timestamp)value);
			}
			
			counter ++;
			System.out.println("customer counter: "+counter);
		}
		
		System.out.println("customer counter: "+counter);
		if(counter > 1) {
			// TODO Ci sono troppi utenti su DB associati alla partita_iva utilizzata
			
			try {

				throw new TooManyCustomersException("Too many customers for one partita_iva. Error Code: "+Constants.ERR_TOO_MANY_CUSTOMERS_FOR_ONE_PARTITA_IVA);
			
			} catch (TooManyCustomersException e) {
				e.printStackTrace();
			}
		} else if (counter == 0) {
			// TODO Non ci sono utenti su DB con la partita iva inserita
			System.out.println("L'utente con partita iva inserita non esiste");
			//customer = null;
		}
		
		if(customer != null)
		{
			System.out.println("customer found: "+found);
			System.out.println("customer returned id: "+customer.getId());
			System.out.println("customer returned uuid: "+customer.getUuid());
			System.out.println("customer returned cf: "+customer.getCodice_fiscale());
			System.out.println("customer returned firstname: "+customer.getFirstname());
			System.out.println("customer returned lastname: "+customer.getLastname());
			System.out.println("customer returned id_ruolo: "+customer.getId_ruolo());
			System.out.println("customer returned enabled: "+customer.isEnabled());
			System.out.println("customer returned data_inserimento: "+customer.getData_inserimento());
		}
		
		return customer;
	}

	public String insertIncompleteUserProfile(Customer customer) throws InvalidPropertiesFormatException, FileNotFoundException, IOException {
		
		String sql = CONF.getPropertyString("customers.insert_incomplete_user_profile");
		System.out.println("sql: "+sql);
		
		String uuid = CommonUtilities.generateUuid();
		
		System.out.println("uuid: "+uuid);
				
		MapSqlParameterSource parameters = new MapSqlParameterSource();
	    parameters.addValue("uuid", uuid);
	    parameters.addValue("partita_iva", customer.getPartita_iva());

	    System.out.println("parameters: "+parameters);
		
	    int inserted = template.update(sql, parameters);
		
		System.out.println("insert ended: "+inserted);
		
		return uuid;
		
	}

	public String findByRagSocCogn(String ragione_sociale_cognome) throws InvalidPropertiesFormatException, FileNotFoundException, IOException, NoClientException {
		
		String uuid = null;
		
		Customer customer = new Customer();
		
		String sql = CONF.getPropertyString("employers.get_all_by_lastname");
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("ragione_sociale", ragione_sociale_cognome);
		parameters.addValue("lastname", ragione_sociale_cognome);
		
		List<Map<String, Object>> found = template.queryForList(sql, parameters);
		System.out.println("users found on the db: "+found.size());
		Iterator<Map<String, Object>> iter = found.iterator();
		
		while (iter.hasNext())
		{
			Map<String, Object> obj = iter.next();
			
			Set keys = obj.keySet();
			Iterator ikeys = keys.iterator();
			
			while(ikeys.hasNext() && (uuid==null))
			{
				String key = (String) ikeys.next();
				System.out.println("customer key: "+key);
				Object value = (Object) obj.get(key);
				System.out.println("customer value: "+value);

				if(key.equals(Constants.Customer_UUID)) 
					uuid = (String) value;
			}
		}
		
		if(uuid == null) {
			throw new NoClientException("Il cliente non esiste. Bisogna creare il cliente prima di concordarci il prezzo di un servizio.");
		}
		
		System.out.println("uuid cliente: "+uuid);;
		return uuid;
	}
}