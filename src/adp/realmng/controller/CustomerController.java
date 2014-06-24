package adp.realmng.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import adp.realmng.dao.CustomerDaoImpl;
import adp.realmng.model.Customer;

@Controller
//@RequestMapping("/cliente")
public class CustomerController{

	@RequestMapping(value = "/cliente", method = RequestMethod.GET)
	public ModelAndView printHome(ModelMap model) {

		System.out.println("Inserisci il cazzo di cliente");
		//model.addAttribute("message", "Inserisci Nuovo Cliente");
		
		Resource r=new ClassPathResource("WEB-INF/deployerConfigContext.xml");

		BeanFactory factory=new XmlBeanFactory(r);

		CustomerDaoImpl dao=(CustomerDaoImpl)factory.getBean("CustomerDao");

//		Collection coll = model.values();
//		System.out.println("values? "+coll.size());		
//		Iterator it = coll.iterator();
//		
//		while(it.hasNext())
//			System.out.println("value: "+it.next());
		
		//Customer customer = new Customer(0, null, 0);

		//CustomerDaoInterface customerDao = new CustomerDaoImpl(null);

		/*try {
			dao.insert(new Customer(23,null,35000));
		} catch (FileNotFoundException e) {

			System.out.println("1");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {

			System.out.println("2");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {

			System.out.println("3");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/


		//return "cliente";
		return new ModelAndView("cliente", "command", new Customer());
	}

	@RequestMapping(value = "/inserisci-cliente", method = RequestMethod.POST)
	public String addCustomer(@ModelAttribute("relationship-management")Customer customer, 
			ModelMap model) {
		System.out.println("Spring Inserisco Utente");
		
		
		System.out.println("nome: "+customer.getNome());
		System.out.println("cognome: "+customer.getCognome());
		System.out.println("email: "+customer.getEmail());
		System.out.println("codice_fiscale: "+customer.getCodice_fiscale());
		
		model.addAttribute("nome", customer.getNome());
		model.addAttribute("surname", customer.getCognome());
		model.addAttribute("email", customer.getEmail());
		model.addAttribute("codice_fiscale", customer.getCodice_fiscale());

		return "result";
	}
	
	//TODO Cancellazione Cliente
	@RequestMapping(value = "/cancella-cliente", method = RequestMethod.POST)
	public String deleteCustomer(@ModelAttribute("relationship-management")Customer customer, 
			ModelMap model) {
		System.out.println("Cancello Cliente");
		
		System.out.println("nome: "+customer.getNome());
		System.out.println("cognome: "+customer.getCognome());
		System.out.println("email: "+customer.getEmail());
		System.out.println("codice_fiscale: "+customer.getCodice_fiscale());
		
		return "result";
	}
	
	//TODO Modifica Cliente
	@RequestMapping(value = "/modifica-cliente", method = RequestMethod.POST)
	public String updateCustomer(@ModelAttribute("relationship-management")Customer customer, 
			ModelMap model) {
		System.out.println("Modifico Cliente");
		
		System.out.println("nome: "+customer.getNome());
		System.out.println("cognome: "+customer.getCognome());
		System.out.println("email: "+customer.getEmail());
		System.out.println("codice_fiscale: "+customer.getCodice_fiscale());
		
		return "result";
	}
	
	//TODO Export Cliente
	@RequestMapping(value = "/esporta-cliente", method = RequestMethod.POST)
	public String exportCustomer(HttpServletResponse response) {
		System.out.println("Esporta Cliente su .csv");
		
		String csvFileName = "books.csv";
		 
        response.setContentType("text/csv");
 
        // creates mock data
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);
 
        /*Customer book1 = new Customer("Effective Java", "Java Best Practices",
                "Joshua Bloch", "Addision-Wesley", "0321356683", "05/08/2008",
                38);
 
        Customer book2 = new Customer("Head First Java", "Java for Beginners",
                "Kathy Sierra & Bert Bates", "O'Reilly Media", "0321356683",
                "02/09/2005", 30);
 
        Customer book3 = new Customer("Thinking in Java", "Java Core In-depth",
                "Bruce Eckel", "Prentice Hall", "0131872486", "02/26/2006", 45);
 
        Customer book4 = new Customer("Java Generics and Collections",
                "Comprehensive guide to generics and collections",
                "Naftalin & Philip Wadler", "O'Reilly Media", "0596527756",
                "10/24/2006", 27);*/
 
        //List<Customer> listBooks = Arrays.asList(book1, book2, book3, book4);
 
        try {
        	
        	// uses the Super CSV API to generate CSV data from the model data
            ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
                    CsvPreference.STANDARD_PREFERENCE);
     
            String[] header = { "Title", "Description", "Author", "Publisher",
                    "isbn", "PublishedDate", "Price" };
            
			csvWriter.writeHeader(header);
			
			csvWriter.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
        /*for (Customer aBook : listBooks) {
            csvWriter.write(aBook, header);
        }*/
		
		return "result";
	}
	
}
