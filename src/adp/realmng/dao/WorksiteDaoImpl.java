package adp.realmng.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.InvalidPropertiesFormatException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import adp.realmng.constants.Constants;
import adp.realmng.model.Customer;
import adp.realmng.model.Worksite;
import adp.realmng.utilities.FileUtilities;

public class WorksiteDaoImpl implements WorksiteInterface{

	SimpleJdbcTemplate template;
	 
	/**
	 * config file where are stored all sql statements
	 */
	private static final FileUtilities CONF = 	
			FileUtilities.factoryConfigFileFromPackageResource(WorksiteDaoImpl.class+"", "/resources/sql-queries.xml");
	
	/**
	 * 	Standard Constructor for Spring Context Initialization
	 */
	public WorksiteDaoImpl(SimpleJdbcTemplate template) {
		this.template = template;
	}
	
	@Override
	public String insert(Worksite worksite) throws InvalidPropertiesFormatException, FileNotFoundException, IOException {
		
		String sql = CONF.getPropertyString("worksites.insert");
		System.out.println("sql: "+sql);
		
		String uuid = generateUuid();
		
		System.out.println("id: "+worksite.getId());
		System.out.println("uuid: "+uuid);
		System.out.println("nome: "+worksite.getNome_cantiere());
		System.out.println("nota: "+worksite.getNota());
		System.out.println("indirizzo: "+worksite.getIndirizzo());
		
		//uuid, nome_cantiere, indirizzo, nota
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
	    parameters.addValue("uuid", uuid);
	    parameters.addValue("nome_cantiere", worksite.getNome_cantiere());
	    parameters.addValue("indirizzo", worksite.getIndirizzo());
	    parameters.addValue("nota", worksite.getNota());
	    
	    System.out.println("parameters: "+parameters);
		
	    int inserted = template.update(sql, parameters);
		
		System.out.println("insert ended: "+inserted);
		
		return uuid;
	}

	
	/**
	 * Method to search for a worksite based on its UUID
	 */
	@Override
	public Worksite findByWorksiteUuid(String uuid) {

		Worksite worksite = new Worksite();
		
		try {
			
			String sql = CONF.getPropertyString("worksites.select_by_uuid");
			
			System.out.println("sql: "+sql);
			
			MapSqlParameterSource parameters = new MapSqlParameterSource();
		    parameters.addValue("uuid", uuid);
			
		    List<Map<String,Object>> worksites = template.queryForList(sql, parameters);
		    
		    Iterator iterWorksites = worksites.iterator();
		    
		    while(iterWorksites.hasNext())
		    {
		    	Map<String, Object> obj = (Map<String, Object>) iterWorksites.next();
		    	
		    	Set keys = obj.keySet();
				Iterator ikeys = keys.iterator();
				
				System.out.println("worksite obj: "+obj);
				
				int counter = 0;
				while(ikeys.hasNext())
				{
					String key = (String) ikeys.next();
					System.out.println("worksite key: "+key);
					Object value = (Object) obj.get(key);
					System.out.println("worksite value: "+value);

					if(key.equals(Constants.Worksite_ID)) 
						worksite.setId((Integer)value);
					if(key.equals(Constants.Worksite_UUID)) 
						worksite.setUuid((String)value);
					if(key.equals(Constants.Worksite_NAME))
						worksite.setNome_cantiere((String)value);
					if(key.equals(Constants.Worksite_INDIRIZZO))
						worksite.setIndirizzo((String)value);
					if(key.equals(Constants.Worksite_NOTA))
						worksite.setNota((String)value);
				}
		    }
			
		} catch (InvalidPropertiesFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return worksite;
	}
	
	@Override
	public Worksite findByWorksiteId(int worksiteId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Worksite findByEmployeeId(int employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> listAllWorksites() throws InvalidPropertiesFormatException, FileNotFoundException, IOException {

		String sql = CONF.getPropertyString("worksites.get_all_by_name");
		
		System.out.println("sql: "+sql);
		
	    List<Map<String,Object>> worksites = template.queryForList(sql);
		
		System.out.println("list of worksites found: "+worksites);
		
		return worksites;
	}

	@Override
	public void modify(Worksite worksite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int deleteByWorksiteId(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * A method to generate an immutable universally unique identifier (UUID).
	 * 
	 * @return A UUID represents a 128-bit value
	 */
	private String generateUuid () {
		return (UUID.randomUUID().toString());
	}
	
}
