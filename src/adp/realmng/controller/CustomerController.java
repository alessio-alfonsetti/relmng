package adp.realmng.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import adp.realmng.constants.Constants;
import adp.realmng.dao.CustomerDaoImpl;
import adp.realmng.model.Customer;
import adp.realmng.model.Prices;

@Controller
public class CustomerController{

	/* Logger */
	Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	/* DAO configuration */
	Resource r=new ClassPathResource("WEB-INF/deployerConfigContext.xml");
	BeanFactory factory = new XmlBeanFactory(r);
	CustomerDaoImpl dao = (CustomerDaoImpl)factory.getBean("CustomerDao");
	
	/* ****************************************************************************************** */
	/* *************************************** CLIENTE ****************************************** */
	/* ****************************************************************************************** */
	
	@RequestMapping(value = "/cliente", method = RequestMethod.GET)
	public ModelAndView printHome(ModelMap model) {
		
		ModelAndView modelAndView = new ModelAndView("cliente/cliente", "command", new Customer()); 
		
		modelAndView.addObject("title", "Inserisci Nuovo Cliente");
		modelAndView.addObject("message", "Inserisci Nuovo Cliente");
		
		/* User Details */
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		modelAndView.addObject("username", userDetails.getUsername());
		
		return modelAndView;
	}

	@RequestMapping(value = "/inserisci-cliente", method = {RequestMethod.POST, RequestMethod.GET})
	public String addCustomer(@ModelAttribute("relationship-management")Customer customer, 
			ModelMap model) {
		
		model.addAttribute("ragione_sociale", customer.getRagione_sociale());
		model.addAttribute("nome", customer.getFirstname());
		model.addAttribute("cognome", customer.getLastname());
		model.addAttribute("email", customer.getEmail());
		model.addAttribute("codice_fiscale", customer.getCodice_fiscale());
		model.addAttribute("partita_iva", customer.getPartita_iva());
		model.addAttribute("codice_fiscale", customer.getCodice_fiscale());
		model.addAttribute("nota", customer.getNota());
		model.addAttribute("numero_cellulare", customer.getNumero_cellulare());
		model.addAttribute("iban", customer.getIban());
		model.addAttribute("indirizzo", customer.getIndirizzo());
		
		try {
			
			String uuid = dao.insert(customer);
			
			customer.setUuid(uuid);
			
			System.out.println("New Customer created with UUID");
			logger.info("New Customer created with UUID: "+uuid);
			
		} catch (FileNotFoundException e) {
			logger.error("File not found.", e);
		} catch (IOException e) {
			logger.error("IOException found", e);
		} catch (Exception e) {
			logger.error("Generic Exception found", e);
		}
		
		model.addAttribute("customer", customer);
		model.addAttribute("title", "Cliente Inserito con successo");
		model.addAttribute("h2", "Le informazioni del cliente inserito sono:");
		model.addAttribute("message", "Le informazioni del cliente inserito sono:");		
		
		/* User Details */
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username", userDetails.getUsername());
		
		return "result";
	}
	
	@RequestMapping(value = "/clienti", method = RequestMethod.GET)
	public String listCustomers(ModelMap model) {
		
		System.out.println("Lista Clienti");
		
		 List<Map<String, Object>> customers = new ArrayList<Map<String, Object>>();
		
		try {
			
			customers = dao.listAllCustomers();
			
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
		
		model.addAttribute("list_customers_by_date_creation", customers);
		
		model.addAttribute("result", "OK");
		model.addAttribute("error", "Clienti trovati con successo");
		
		model.addAttribute("message", "Lista Clienti:");
		model.addAttribute("title", "Lista Clienti");
		
		/* User Details */
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username", userDetails.getUsername());
		
		return "cliente/lista-clienti";		
	}
	
	//TODO Cancellazione Cliente
	@RequestMapping(value = "/cancella-cliente", method = RequestMethod.POST)
	public String deleteCustomer(@ModelAttribute("relationship-management")Customer customer, 
			ModelMap model) {
		System.out.println("Cancello Cliente");
		
		System.out.println("nome: "+customer.getFirstname());
		System.out.println("cognome: "+customer.getLastname());
		System.out.println("email: "+customer.getEmail());
		System.out.println("codice_fiscale: "+customer.getCodice_fiscale());
		
		/* User Details */
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username", userDetails.getUsername());
		
		return "result";
	}
	
	//TODO Modifica Cliente
	@RequestMapping(value = "/modifica-cliente", method = RequestMethod.POST)
	public String updateCustomer(@ModelAttribute("relationship-management")Customer customer, 
			ModelMap model) {
		System.out.println("Modifico Cliente");
		
		System.out.println("nome: "+customer.getFirstname());
		System.out.println("cognome: "+customer.getLastname());
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
	
	/* ****************************************************************************************** */
	/* *************************************** DIPENDENTE *************************************** */
	/* ****************************************************************************************** */
	
	@RequestMapping(value = "/dipendente", method = RequestMethod.GET)
	public ModelAndView printHomeEmployee(ModelMap model) {
		return new ModelAndView("dipendente/dipendente", "command", new Customer());
	}
	
	@RequestMapping(value = "/inserisci-dipendente", method = RequestMethod.POST)
	public String addEmployee(@ModelAttribute("relationship-management")Customer customer, 
			ModelMap model) {
				
		System.out.println("Spring Inserisco Dipendente");
		System.out.println("ruolo: "+customer.getId_ruolo());
		
		model.addAttribute("ruolo", customer.getId_ruolo());
		model.addAttribute("nome", customer.getFirstname().trim().toUpperCase());
		model.addAttribute("cognome", customer.getLastname().trim().toUpperCase());
		model.addAttribute("email", customer.getEmail());
		model.addAttribute("codice_fiscale", customer.getCodice_fiscale().trim().toUpperCase());
		model.addAttribute("nota", customer.getNota().trim().toUpperCase());
		model.addAttribute("numero_cellulare", customer.getNumero_cellulare());
		model.addAttribute("indirizzo", customer.getIndirizzo().trim().toUpperCase());
		model.addAttribute("iban", customer.getIban().trim().toUpperCase());
	    
		try {
			
			String uuid = dao.insert(customer);
			
			System.out.println("New Customer created with UUID");
			logger.info("New Customer created with UUID: "+uuid);
			
			model.addAttribute("customer", customer);
			
		} catch (FileNotFoundException e) {
			logger.error("File not found.", e);
		} catch (IOException e) {
			logger.error("IOException found", e);
		} catch (Exception e) {
			logger.error("Generic Exception found", e);
		}
		
		List<Map<String, Object>> customerCreated = new ArrayList<Map<String, Object>>();
		
		Iterator<Map<String, Object>> iter = customerCreated.iterator();
		
		while(iter.hasNext())
		{
			Map<String, Object> keysvalues = iter.next();

			keysvalues.put(Constants.Customer_IDRUOLO, customer.getId_ruolo());
			
			if(customer.getFirstname() != null)
				keysvalues.put(Constants.Customer_FIRSTNAME, customer.getFirstname());
			
			if(customer.getLastname() != null)
				keysvalues.put(Constants.Customer_LASTNAME, customer.getLastname());
			
			if(customer.getEmail() != null)
				keysvalues.put(Constants.Customer_EMAIL, customer.getEmail());

			if(customer.getCodice_fiscale() != null)
				keysvalues.put(Constants.Customer_CODICE_FISCALE, customer.getCodice_fiscale());
			
			if(customer.getNota() != null)
				keysvalues.put(Constants.Customer_NOTA, customer.getNota());
			
			if(customer.getNumero_cellulare() != null)
				keysvalues.put(Constants.Customer_NUMERO_CELLULARE, customer.getNumero_cellulare());
			
			if(customer.getIndirizzo() != null)
				keysvalues.put(Constants.Customer_INDIRIZZO, customer.getIndirizzo());
			
			if(customer.getIban() != null)
				keysvalues.put(Constants.Customer_IBAN, customer.getIban());
			
			customerCreated.add(keysvalues);
			
		}
		
		model.addAttribute("title", "Dipendente Inserito con successo");
		model.addAttribute("h2", "Le informazioni del dipendente inserito sono:");
		model.addAttribute("utente_creato", "Le informazioni del dipendente inserito sono:");
		model.addAttribute("message", "Clienti trovati con successo sono:");
		
		/* User Details */
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username", userDetails.getUsername());
		
		return "result";
	}
	
	@RequestMapping(value = "/dipendenti", method = RequestMethod.GET)
	public String listEmployers(ModelMap model) {
		
		System.out.println("Lista Dipendenti");
		
		List<Map<String, Object>> employers = new ArrayList<Map<String, Object>>();
		
		try {
			
			employers = dao.listAllEmployers();
			
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
		
		model.addAttribute("list_employers_by_lastname", employers);
		
		model.addAttribute("result", "OK");
		model.addAttribute("error", "Error /dipendenti");
		model.addAttribute("title", "Lista Dipendenti");
		model.addAttribute("message", "Lista Dipendenti");
		model.addAttribute("h2", "Lista Dipendenti:");		
		
		/* User Details */
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username", userDetails.getUsername());
		
		return "dipendente/lista-dipendenti";		
	}
	
	@RequestMapping(value = "/cancella-dipendente", method = {RequestMethod.POST})
	public String deleteEmployer(@RequestParam("uuid") String uuid, 
			ModelMap model) {
		
		System.out.println("Cancello Cliente "+ uuid);
		
		Customer customer = new Customer();
		
		model.addAttribute("message", "Disattivazione Cliente:");
		model.addAttribute("title", "Disattivazione Cliente:");
		model.addAttribute("message_detail", "Il profilo cliente é stato disattivato con successo.");
		
		/* User Details */
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username", userDetails.getUsername());
		
		try {
			
			int res = dao.deleteByUuid(uuid);
			
			if (res > 0)
			{
				System.out.println("Deattivati "+ res +" utente/i");
				logger.debug("Deattivati "+res+" utente/i.");
				
				customer = dao.findByCustomerUuid(uuid);
				
				model.addAttribute("customer", customer);
			}
				
		} catch (InvalidPropertiesFormatException e) {
			model.addAttribute("error", ("Errore nella deattivazione dell'utente con UUID: "+uuid));
			logger.error("Errore nella deattivazione dell'utente "+uuid+". Error Description is: "+e.getMessage());
		} catch (FileNotFoundException e) {
			model.addAttribute("error", ("Errore nella deattivazione dell'utente con UUID: "+uuid));
			logger.error("Errore nella deattivazione dell'utente "+uuid+". Error Description is: "+e.getMessage());
		} catch (IOException e) {
			model.addAttribute("error", ("Errore nella deattivazione dell'utente con UUID: "+uuid));
			logger.error("Errore nella deattivazione dell'utente "+uuid+". Error Description is: "+e.getMessage());
		}
		
		return "result";
	}
	
	@RequestMapping(value = "/gestisci-dipendente", method = {RequestMethod.POST})
	public String dailyEmployerManagement(@RequestParam("uuid") String uuid, 
			ModelMap model) {
		
		System.out.println("Gestisco Utente "+ uuid);
		
		Customer customer = new Customer();
		
		model.addAttribute("message", "Aggiorna Profilo Dipendente:");
		model.addAttribute("title", "Aggiorna Profilo Dipendente:");
		
		/* User Details */
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username", userDetails.getUsername());
		
		//model.addAttribute("message_detail", "Il profilo cliente é stato disattivato con successo.");
		
		try {
			
			customer = dao.findByCustomerUuid(uuid);
			model.addAttribute("customer", customer);
			
		} catch (InvalidPropertiesFormatException e) {
			model.addAttribute("error", ("Errore nella deattivazione dell'utente con UUID: "+uuid));
			logger.error("Errore nella deattivazione dell'utente "+uuid+". Error Description is: "+e.getMessage());
		} catch (FileNotFoundException e) {
			model.addAttribute("error", ("Errore nella deattivazione dell'utente con UUID: "+uuid));
			logger.error("Errore nella deattivazione dell'utente "+uuid+". Error Description is: "+e.getMessage());
		} catch (IOException e) {
			model.addAttribute("error", ("Errore nella deattivazione dell'utente con UUID: "+uuid));
			logger.error("Errore nella deattivazione dell'utente "+uuid+". Error Description is: "+e.getMessage());
		}
		
		return "dipendente/gestisci-dipendente";
	}
	
	@RequestMapping(value = "/trova-cliente", method = {RequestMethod.POST, RequestMethod.GET}, params = {"ragSocCogn"})
	public String searchCustomer(ModelMap model, @RequestParam("ragSocCogn") String ragSocCogn){
		
		Customer customer = new Customer();
		
		model.addAttribute("message", "Profilo ricercato");
		model.addAttribute("title", "Profilo ricercato");
		
		/* User Details */
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username", userDetails.getUsername());
		
		try {
			customer = dao.findByCustomerSurnameCompanyName(ragSocCogn);
			model.addAttribute("searched_customer", customer);
			
			if(customer.getId()==0)
				System.out.println("Il cliente non e' stato trovato");
			
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
		
		return "result";
	}
	
	/**
	 * ragione_sociale
	 * firstname
	 * lastname
	 * email
	 * partita_iva
	 * codice_fiscale
	 * nota
	 * password
	 * numero_cellulare
	 * iban
	 * indirizzo
	 * id_ruolo
	 * 
	 * @param model
	 * @param uuid
	 * @return
	 */
	@RequestMapping(value="/completa-profilo", method = RequestMethod.POST, params = "uuid")
	public String upgradeProfile (ModelMap model, @RequestParam("uuid") String uuid){
		
		/* User Details */
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username", userDetails.getUsername());
		
		model.addAttribute("title", "Completa Profilo del Cliente");
		
		System.out.println("UUID: "+uuid);
		Customer customer;
		
		try {
			
			customer = dao.findByCustomerUuid(uuid);
			if(customer == null)
				model.addAttribute("message", "Il Cliente cercato non esiste");
						
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
		
		return null;
	}
	
	@RequestMapping(value="modifica-cliente", method = RequestMethod.POST, params = "uuid")
	public String updateCustomer (ModelMap model, @RequestParam("uuid") String uuid) {
		
		/* User Details */
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username", userDetails.getUsername());
		
		model.addAttribute("title", "Completa Profilo del Cliente");
		
		System.out.println("UUID: "+uuid);
		Customer customer;
		
		try {
			
			customer = dao.findByCustomerUuid(uuid);
			model.addAttribute("customer", customer);
			
			if(customer == null) 
				model.addAttribute("message", "Il Cliente cercato non esiste");
			else model.addAttribute("message", "Il Cliente da modificare e':");
						
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
		
		return "cliente/modifica-cliente";
	}
	
}
