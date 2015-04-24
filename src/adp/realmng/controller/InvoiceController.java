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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	InvoiceDaoImpl invoicedao = (InvoiceDaoImpl)factory.getBean("InvoiceDao");
	CustomerDaoImpl customerdao = (CustomerDaoImpl)factory.getBean("CustomerDao");	
	
	@RequestMapping(value = "/fattura", method = RequestMethod.GET)
	public ModelAndView printHome(ModelMap model) {
		return new ModelAndView("fattura", "command", new Customer());
	}
	
	/**
	 * Controller per la gestione dell'inserimento di una nuova fattura
	 * 	
	 * @param invoice
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/inserisci-fattura", method = RequestMethod.POST)
	public /*ModelAndView*/ String addInvoice(/*ModelAndView modelAndView,*/ 
			@ModelAttribute("relationship-management")Invoice invoice, 
			ModelMap model) {
		
		System.out.println("Spring Inserisco Fattura");
		
		try {
			
			/* Prendo utente dalla tabella utenti, utilizzando la partita_iva inserita in fase di creazione della fattura */
			Customer customer = customerdao.findByParatitaIva(invoice.getPartita_iva());
			
			System.out.println("dopo findByPartitaIva: ");
			
			String customeruuid = null;
			// Caso in cui il cliente per cui si sta facendo la fattura non sia presente su DB
			if (customer == null)
			{
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
			
			model.addAttribute("uuid", invoice.getUuid());
			model.addAttribute("descrizione", invoice.getDescrizione());
			model.addAttribute("importo", invoice.getImporto());
			model.addAttribute("iva", invoice.getIva());
			model.addAttribute("importo_totale", invoice.getImporto_totale());
			model.addAttribute("partita_iva", invoice.getPartita_iva());
			model.addAttribute("nome_cantiere", invoice.getNome_cantiere());
			
			System.out.println("Inserita Fattura con UUID: "+invoiceuuid);
			logger.info("Inserita Fattura con UUID: "+invoiceuuid);
			
		} catch (FileNotFoundException e) {
			model.addAttribute("result", "KO");
			model.addAttribute("error", "FileNotFoundException");
			//return new ModelAndView("fattura-esito");
			return "fattura-esito";
		} catch (IOException e) {
			model.addAttribute("result", "KO");
			model.addAttribute("error", "IOException");
			return "fattura-esito";
			//return new ModelAndView("fattura-esito");
		} catch (Exception e) {
			model.addAttribute("result", "KO");
			model.addAttribute("error", "Exception");
			return "fattura-esito";
			//return new ModelAndView("fattura-esito");
		}
		
		//generazione del report di una fattura che verrá memorizzata su DB
		//invoicedao.generateReport(invoice);	
		
		model.addAttribute("result", "OK");
		model.addAttribute("error", "Fattura inserita con successo");
		
		//modelAndView = new ModelAndView("/report/pdf");
		//modelAndView.addObject("invoice", model);
		
		//return "report/pdf";
		return "fattura-esito";
		//return modelAndView;
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
		
		return "fattura/lista-fatture";

	}
	
}
