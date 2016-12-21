package adp.realmng.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Map;

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

import adp.realmng.dao.CustomerDaoImpl;
import adp.realmng.dao.InvoiceDaoImpl;
import adp.realmng.model.Customer;
import adp.realmng.model.Invoice;

@Controller
public class InvoiceController {

	/* Logger */
	Logger logger = LoggerFactory.getLogger(InvoiceController.class);
	
	/* DAO configuration */
	Resource r=new ClassPathResource("WEB-INF/deployerConfigContext.xml");
	BeanFactory factory = new XmlBeanFactory(r);
	
	/* DAO Objects */
	InvoiceDaoImpl invoicedao = (InvoiceDaoImpl)factory.getBean("InvoiceDao");
	CustomerDaoImpl customerdao = (CustomerDaoImpl)factory.getBean("CustomerDao");	
	
	@RequestMapping(value = "/fattura", method = RequestMethod.GET)
	public ModelAndView printHome(ModelMap model) {
		
		ModelAndView modelAndView = new ModelAndView("fattura/fattura", "command", new Customer());
		
		/* User Details */
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		modelAndView.addObject("username", userDetails.getUsername());
		
		return modelAndView;
	}
	
	/**
	 * Gestione dell'inserimento di una nuova fattura
	 * 	
	 * @param invoice
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/inserisci-fattura", method = {RequestMethod.POST, RequestMethod.GET})
	public String addInvoice(@ModelAttribute("relationship-management")Invoice invoice, 
			ModelMap model) {
		
		try {
			
			/* Prendo utente dalla tabella utenti, utilizzando la partita_iva inserita in fase di creazione della fattura */
			Customer customer = customerdao.findByPartitaIva(invoice.getPartita_iva());
			
			System.out.println("dopo findByPartitaIva: ");
			
			String customeruuid = null;
			// Caso in cui il cliente per cui si sta facendo la fattura non sia presente su DB
			if (customer == null || customer.getId() == 0)
			{
				if(customer == null)
				{
					// TODO throw custom exception
					throw new Exception("Si é verificato un errore. Contatta il supporto indicando la data e l'ora in cui é avvenuto l'errore.");					
				}
				else {
					System.out.println("customer null");
					// poiché il customer non é presente su DB, lo inserisco con informazioni di default - verrá successivamente modificato dall'operatore
					customeruuid = customerdao.insertIncompleteUserProfile(customer);
					
					/* ottengo il customer appena inserito utilizzando il customeruuid creato al passaggio precedente al fine di utilizzarne l'id come FK nella
					 * creazione di un oggetto invoice
					 */
					customer = customerdao.findByCustomerUuid(customeruuid);
					//customerid = customerdao.findByCustomerUuid(customeruuid);
					
					System.out.println("customer id: "+customer.getUuid());
				}
			}
			
			System.out.println("dopo customer null: "+customer.getUuid());
			
			invoice.setId_utente(customer.getId());
			
			System.out.println("partita_iva: "+invoice.getPartita_iva());
			System.out.println("descrizione: "+invoice.getDescrizione());
			System.out.println("importo: "+invoice.getImporto());
			System.out.println("iva: "+invoice.getIva());
			System.out.println("importo_totale: "+invoice.getImporto_totale());
			System.out.println("nome_cantiere: "+invoice.getNome_cantiere());
			System.out.println("id_utente: "+invoice.getId_utente());
			
			String invoiceuuid = invoicedao.insert(invoice);
			System.out.println("invoiceuuid: "+invoiceuuid);
			invoice.setUuid(invoiceuuid);
			
			System.out.println("invoice id: "+invoicedao.findByInvoiceUuid(invoiceuuid).getId());
			model.addAttribute("id", invoicedao.findByInvoiceUuid(invoiceuuid).getId());
			
			model.addAttribute("uuid", invoice.getUuid());
			System.out.println("invoice uuid: "+invoice.getUuid());
			model.addAttribute("descrizione", invoice.getDescrizione());
			model.addAttribute("importo", invoice.getImporto());
			model.addAttribute("iva", invoice.getIva());
			model.addAttribute("importo_totale", invoice.getImporto_totale());
			model.addAttribute("partita_iva", invoice.getPartita_iva());
			model.addAttribute("nome_cantiere", invoice.getNome_cantiere());
			
			model.addAttribute("result", "OK");
			model.addAttribute("title", "Inserimento Fattura");
			model.addAttribute("message", "Fattura Registrata Correttamente");
			
			/* User Details */
			UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("username", userDetails.getUsername());
			
			System.out.println("Inserita Fattura con UUID: "+invoiceuuid);
			logger.info("Inserita Fattura con UUID: "+invoiceuuid);
			
		} catch (FileNotFoundException e) {
			model.addAttribute("result", "KO");
			model.addAttribute("message", "FileNotFoundException");
			return "fattura/gestisci-fattura";
		} catch (IOException e) {
			model.addAttribute("result", "KO");
			model.addAttribute("message", "IOException");
			return "fattura/gestisci-fattura";
		} catch (Exception e) {
			model.addAttribute("result", "KO");
			model.addAttribute("message", "Exception");
			return "fattura/gestisci-fattura";
		}
		
		return "fattura/gestisci-fattura";
	}
	
	/**
	 * Controller per la gestione dell'inserimento di una nuova fattura
	 * 	
	 * @param invoice
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/aggiorna-fattura", method = RequestMethod.POST)
	public String updateInvoice(@ModelAttribute("relationship-management")Invoice invoice, 
			ModelMap model) {
		
		//TODO Update della fattura
		
		System.out.println("UPDATE DELLA FATTURA DA FARE");
		
		return null;
	}
	
	/**
	 * Controller per la gestione dell'inserimento di una nuova fattura
	 * 	
	 * @param invoice
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/carica-fattura", method = RequestMethod.POST)
	public String uploadInvoice(@ModelAttribute("relationship-management")Invoice invoice, 
			ModelMap model) {
		
		model.addAttribute("title", "Carica Fattura");
		model.addAttribute("message", "Carica Fattura");
		
		/* User Details */
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username", userDetails.getUsername());
		
		return "fattura/carica-fattura";
	}
	
	/**
	 * Controller per la gestione della home delle fatture. Vengono ricercate 
	 * le 10 Fatture emesse piú recentemente.
	 * 	
	 * @param model
	 * 
	 * @return esito dell'operazione+descrizione
	 */
	@RequestMapping(value = "/fatture", method = RequestMethod.GET)
	public String searchLastTenInvoices(ModelMap model) {
		
		System.out.println("Ricerca le 10 Fatture emesse piú recentemente per la 'homepage fatture'");
		
		List<Map<String, Object>> invoices = null;
		
		try {
			
			invoices = invoicedao.findLatestTenInvoices();
			
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
		
		model.addAttribute("list_ten_invoices_by_date_emission", invoices);
		
		model.addAttribute("result", "OK");
		model.addAttribute("error", "Fattura inserita con successo");
		model.addAttribute("title", "Lista Fatture");
		model.addAttribute("message", "Lista Fatture:");
		
		/* User Details */
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username", userDetails.getUsername());
		
		return "fattura/lista-fatture";

	}
	
	/**
	 * Controller per la gestione della home delle fatture. Vengono ricercate 
	 * le 10 Fatture emesse piú recentemente.
	 * 	
	 * @param model
	 * 
	 * @return esito dell'operazione+descrizione
	 */
	@RequestMapping(value = "/fatture-cliente", method = {RequestMethod.GET, RequestMethod.POST}, params = "uuid")
	public String searchInvoicesByCustomerId(@RequestParam("uuid") String uuid,
			ModelMap model) {
		
		System.out.println("Ricerca le Fatture emessa a cliente con id: "+uuid);
		
		List<Map<String, Object>> invoices = null;
		
		Customer customer = new Customer();
		
		try {
			
			customer = customerdao.findByCustomerUuid(uuid);
			System.out.println("fatture-cliente; Id cliente"+customer.getId());
			invoices = invoicedao.findInvoicesByCustomerId(customer.getId());
			
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
		
		model.addAttribute("list_ten_invoices_by_date_emission", invoices);
		
		model.addAttribute("result", "OK");
		model.addAttribute("error", "Fattura inserita con successo");
		model.addAttribute("message", "Lista Fatture emesse per il cliente "+customer.getFirstname()+" "+customer.getLastname()+" con PI/CF "+customer.getPartita_iva()+"/"+customer.getCodice_fiscale());
		model.addAttribute("title", "Lista Fatture Emeese per cliente");
		
		/* User Details */
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username", userDetails.getUsername());
		
		return "fattura/lista-fatture";

	}
	
}
