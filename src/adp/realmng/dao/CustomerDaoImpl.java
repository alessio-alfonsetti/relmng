package adp.realmng.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import adp.realmng.constants.CustomerConstants;
import adp.realmng.model.Customer;
import adp.realmng.utilities.FileUtilities;
 
public class CustomerDaoImpl implements CustomerInterface{

	//private DataSource dataSource;
	SimpleJdbcTemplate template;
 
	/**
	 * config file where are stored all sql statements
	 */
	private static final FileUtilities CONF = 	
			FileUtilities.factoryConfigFileFromPackageResource(CustomerDaoImpl.class+"", "/resources/sql-queries.xml");
	
//	public void setDataSource(DataSource dataSource) {
//		this.dataSource = dataSource;
//	}
 
	/**
	 * 	Standard Constructor for Spring Context Initialization
	 */
	public CustomerDaoImpl(SimpleJdbcTemplate template) {
		this.template = template;
	}
	
	public void insert(Customer customer) throws Exception, FileNotFoundException, IOException{
 
		//String sql = "INSERT INTO relmng_t_cliente " + "(uuid, nome, cognome) VALUES (?, ?, ?)";
		
		String sql = CONF.getPropertyString("customers.insert");
		
		System.out.println("sql: "+sql);
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
	    parameters.addValue("uuid", "000000000001");
	    parameters.addValue("email", "alessio.alfonsetti@gmail.com");
	    parameters.addValue("password", "xxxx");
	    parameters.addValue("nome", "Alessio");
	    parameters.addValue("cognome", "Alfonsetti");
	    parameters.addValue("indirizzo_uuid", "2");
	    parameters.addValue("tipo_uuid", "2");
	    parameters.addValue("note", "test");
	    parameters.addValue("pi", "01801520667");
	    parameters.addValue("cf", "LFNLSS82D16A345I");
	    parameters.addValue("num_tel", "0862 68282");
	    parameters.addValue("num_fax", "0862 68283");
	    parameters.addValue("num_cell", "340 5454649");
		
	    int inserted = template.update(sql, parameters);
		
		System.out.println("insert ended: "+inserted);
		
	}

	@Override
	public Customer findByCustomerId(int custId) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * This method retrieves all the customers by surname
	 */
	@Override
	public List<Map<String, Object>> listAllCustomers () throws InvalidPropertiesFormatException, FileNotFoundException, IOException {

		String sql = CONF.getPropertyString("customers.get_all_by_name");
		
		System.out.println("sql: "+sql);
		
	    List<Map<String,Object>> customers = template.queryForList(sql);
		
		System.out.println("list of customers found: "+customers);
		
		return customers;
	}
 
	public Customer parseCustomer(Map<String, Object> listEntry_)
	{
		Customer customer = new Customer();
		
		while(!listEntry_.isEmpty())
			customer.setUuid((Integer) listEntry_.get(CustomerConstants.Customer_UUID));
					
		return customer;
	}

	@Override
	public void delete(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modify(Customer customer) {
		// TODO Auto-generated method stub
		
	}
	
	/*public Customer findByCustomerId(int custId){
 
		String sql = "SELECT * FROM relmng_t_cliente WHERE email = ?";
 
		Connection conn = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, custId);
			Customer customer = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				customer = new Customer(
					rs.getString("email"),
					rs.getString("nome"), 
					rs.getInt("uuid")
				);
			}
			rs.close();
			ps.close();
			
			System.out.println("customer: "+customer);
			
			return customer;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
	}

	
	public Customer listAllCustomers() {

		String sql = "SELECT * FROM relmng_t_cliente";
		 
		Connection conn = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			Customer customer = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				customer = new Customer(
					rs.getString("nome"), 
					rs.getString("email"),
					rs.getInt("uuid")
				);
			}
			rs.close();
			ps.close();
			return customer;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
		
	}*/
}