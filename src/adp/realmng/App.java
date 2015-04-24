package adp.realmng;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import adp.realmng.dao.CustomerDaoImpl;
import adp.realmng.model.Customer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws FileNotFoundException, IOException, Exception
    {
    	ApplicationContext context = new ClassPathXmlApplicationContext("../application-context.xml");
    	
    	//ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"realmng-servlet.xml"});
    	
        CustomerDaoImpl customerDAO = (CustomerDaoImpl) context.getBean("customerDaoImpl");
        Customer customer = new Customer("alessio.alfonsetti@hotmail.it", "Alessio", "000000000001");
        customerDAO.insert(customer);
        System.out.println("customer inserted: ");
        Customer customer1 = customerDAO.findByCustomerId(000000000001);
        System.out.println("customer found: "+customer1);
        
    }
}
