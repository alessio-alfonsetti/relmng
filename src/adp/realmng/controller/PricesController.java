package adp.realmng.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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
import adp.realmng.dao.PricesDaoImpl;
import adp.realmng.exceptions.NoClientException;
import adp.realmng.model.Customer;
import adp.realmng.model.Prices;

@Controller
public class PricesController {

	/* Logger */
	Logger logger = LoggerFactory.getLogger(GATransportMUDController.class);
	
	/* DAO configuration */
	Resource r=new ClassPathResource("WEB-INF/deployerConfigContext.xml");
	BeanFactory factory = new XmlBeanFactory(r);
	
	PricesDaoImpl pricesDao = (PricesDaoImpl)factory.getBean("PricesDao");
	CustomerDaoImpl customerDao = (CustomerDaoImpl)factory.getBean("CustomerDao");
	
	/**
	 * Inserimento prezzo per cliente, passando le info del cliente stesso: 
	 * @param clientUUID
	 * @param ragSoc
	 * @param lastname
	 * @param firstname
	 * @return
	 */
	@RequestMapping(value = "/listino-prezzi", method = {RequestMethod.GET, RequestMethod.POST}, params = {"uuid", "ragione_sociale", "lastname", "firstname"})
	public String listPrices(@ModelAttribute("relationship-management") Prices record, ModelMap model, @RequestParam("uuid") String clientUUID,
			@RequestParam("ragione_sociale") String ragSoc, @RequestParam("lastname") String lastname, @RequestParam("firstname") String firstname) {
		
		System.out.println("Lista Prezzi per cliente");
		List<Map<String, Object>> prices = new ArrayList<Map<String, Object>>();
		String toBeReturned = "listino/prezzi-cliente";

		model.addAttribute("result", "OK");
		
		model.addAttribute("message", "Gestisci Listino prezzi per il Cliente ");
		model.addAttribute("title", "Listino Prezzi Cliente");
		
		/* User Details */
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username", userDetails.getUsername());

		if(ragSoc==null || ragSoc.length()==0) {
			model.addAttribute("nome_cliente", firstname.toUpperCase()+" "+lastname.toUpperCase());
			model.addAttribute("rag_soc_cogn", firstname.toUpperCase()+" "+lastname.toUpperCase());
		} else {
			model.addAttribute("nome_cliente", ragSoc.toUpperCase());
			model.addAttribute("rag_soc_cogn", ragSoc.toUpperCase());
		}

		model.addAttribute("result", "OK");
		model.addAttribute("error", "Prezzi trovati con successo");
		
		model.addAttribute("message", "Lista Prezzi per Cliente:");
		model.addAttribute("title", "Lista Prezzi per Cliente");
		
		try {
			
			prices = pricesDao.findPricesByClientUuid(clientUUID);
			
			if(prices.size()==0){
				System.out.println("L'utente non ha un listino prezzi. Creane uno");
				model.addAttribute("message", "L'utente non ha un listino prezzi. Creane uno");
				toBeReturned = "listino/inserisci-listino";
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
		
		model.addAttribute("list_prices_by_customer", prices);
		
		return toBeReturned;
	}
	
	@RequestMapping(value = "gestisci-listino", method = {RequestMethod.GET, RequestMethod.POST}, params = "uuid")
	public String searchPricesByCustomerId(@RequestParam("uuid") String uuid,
			ModelMap model) {
		
		System.out.println("Ricerco il Listino Prezzi per cliente: "+uuid);
		
		List<Map<String, Object>> prices = null;
		
		Customer customer = new Customer();
		
		try {
			
			customer = customerDao.findByCustomerUuid(uuid);
			System.out.println("fatture-cliente; Id cliente"+customer.getId());
			prices = pricesDao.findPricesByClientUuid(customer.getUuid());
			
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
		
		model.addAttribute("list_prices_by_client_id", prices);
		
		model.addAttribute("result", "OK");
		model.addAttribute("error", "Fattura inserita con successo");
		model.addAttribute("message", "Lista Fatture emesse per il cliente "+customer.getFirstname()+" "+customer.getLastname()+" con PI/CF "+customer.getPartita_iva()+"/"+customer.getCodice_fiscale());
		model.addAttribute("title", "Lista Fatture Emeese per cliente");
		
		/* User Details */
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username", userDetails.getUsername());
		
		return "result";

	}
	
	/**
	 * Inserimento prezzi per cliente.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/listino-prezzi", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView printHome(ModelMap model) {
		
		ModelAndView modelAndView = new ModelAndView("listino/inserisci-listino", "command", new Prices()); 
		
		modelAndView.addObject("title", "Inserisci Nuovo Prezzo nel Listino");
		modelAndView.addObject("message", "Inserisci Nuovo Prezzo nel Listino");
		
		/* User Details */
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		modelAndView.addObject("username", userDetails.getUsername());
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/inserisci-listino", method = {RequestMethod.POST, RequestMethod.GET})
	public String addInListino(@ModelAttribute("relationship-management")Prices prices, ModelMap model) {
		
		String resultURL = "result";
		
		model.addAttribute("cer", prices.getCer());
		model.addAttribute("cer_descr", prices.getCer_descr());
		model.addAttribute("imponibile", prices.getImponibile());
		model.addAttribute("iva", prices.getIva());
		model.addAttribute("totale", prices.getTotale());
		System.out.println("uuid_cliente: "+prices.getUuid_cliente());
		
		try {
			
			/* User Details */
			UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("username", userDetails.getUsername());
			
			String uuid_cliente = customerDao.findByRagSocCogn(prices.getUuid_cliente());
			model.addAttribute("uuid_cliente", uuid_cliente);
			prices.setUuid_cliente(uuid_cliente);
			
			model.addAttribute("last_update", prices.getLast_update());
			model.addAttribute("nota_update", prices.getNota_update());
			
			List<Map<String, Object>> pricesFound = pricesDao.findPricesByCer(prices.getCer());
			
			if(pricesFound.size() == 0) {
				System.out.println("Inserimento corretto per il CER");
				// non ci sono prezzi per il prodotto considerato. Inserisci normalmente
				model.addAttribute("prices", prices);
				model.addAttribute("title", "Prezzo aggiunto con successo nel Listino");
				model.addAttribute("title2", "Le informazioni inserite sono:");
				model.addAttribute("message", "Le informazioni inserite sono:");
				
				pricesDao.insert(prices);
				
			} else {
				System.out.println("E' stato trovato un prezzo con il CER usato. Modifica il precedente.");
				// se prezzo = 1, vuol dire che il prodotto ha gia' un prezzo e non si deve permettere un altro inserimento. Si restituisce all'operatore
				// il prezzo esistente per la modifica.
				// se prezzo > 1 redirigi alla pagina di modifica del prezzo gia' esistente per questo CER
				model.addAttribute("prices", pricesFound);
				model.addAttribute("title", "Prezzi Trovati");
				model.addAttribute("title2", "Ecco i prezzi trovati per il CER inserito: ");
				model.addAttribute("message", "Un solo prezzo e' permesso per il CER. Cancella i prezzi, mantenendone solo uno.");
				
				resultURL = "listino/prezzi-duplicati";
				
			} 
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoClientException e ) {

			// redirigi alla pagina per la creazione del cliente
			System.out.println("Error: "+e.getMessage());
			model.addAttribute("title2", "Il cliente non esiste. Bisogna creare il cliente prima di concordarci il prezzo di un servizio.");
			model.addAttribute("message", "Inserisci Nuovo Cliente");
			
			resultURL = "cliente/cliente";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		System.out.println("Reindirizza a: "+resultURL);
		
		return resultURL;
	}
	
	@RequestMapping(value = "/modifica-listino", method = {RequestMethod.POST, RequestMethod.GET})
	public String upgradeListino(@ModelAttribute("relationship-management") Prices record, ModelMap model){
		
		/* User Details */
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username", userDetails.getUsername());
		
		System.out.println("Listino Prezzi in ordine Decrescente");
		List<Map<String, Object>> prices = new ArrayList<Map<String, Object>>();
		String toBeReturned = "listino/modifica-prezzo";

		model.addAttribute("result", "OK");
		
		model.addAttribute("message", "Modifica Listino Prezzi");
		model.addAttribute("title", "Modifica Listino Prezzi");
		
		try {
			
			prices = pricesDao.findPricesOrderedByNewest();
			model.addAttribute("latest_prices", prices);

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
		
		return toBeReturned;
	}

	/**
	 * @params:
	 * 	u  = uuid
	 *  uc = uuid_cliente
	 *  c  = cer
	 *  cd = cer_descrizione
	 *  im = imponibile
	 *  iv = iva
	 *  t  = totale
	 *  lu = last_update
	 *  nu = not_update
	 */
	@RequestMapping(value = "/modifica-listino", method = {RequestMethod.POST, RequestMethod.GET}, params = {"u", "uc", "c", "cd", "im", "iv", "t", "lu", "nu"} )
	public String upgradeListino(@ModelAttribute("relationship-management") Prices record, ModelMap model, 
			String u, String uc, String c, String cd, String im, String iv, String t, String lu, String nu){
		
		/* User Details */
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username", userDetails.getUsername());
		
		System.out.println("Listino Prezzi Cliente UUID: "+uc);
		String toBeReturned = "listino/modifica-prezzo";
		
		model.addAttribute("latest_prices", "modificare");
		model.addAttribute("u", u);
		model.addAttribute("uc", uc);
		model.addAttribute("c", c);
		model.addAttribute("cd", cd);
		model.addAttribute("im", im);
		model.addAttribute("iv", iv);
		model.addAttribute("t", t);
		model.addAttribute("lu", lu);
		model.addAttribute("nu", nu);
		
		model.addAttribute("latest_prices", "modificare");
		
		model.addAttribute("message", "Modifica Prezzo");
		model.addAttribute("title", "Modifica Prezzo");
		
		return toBeReturned;
	}
	
	@RequestMapping(value = "/modifica-prezzo", method = {RequestMethod.POST, RequestMethod.GET})
	public String updatePrice(@ModelAttribute("relationship-management") Prices prices, ModelMap model) {
		
		/* User Details */
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username", userDetails.getUsername());
		model.addAttribute("title", "Modifica Prezzo");
		
		model.addAttribute("uuid", prices.getUuid());
		model.addAttribute("uuid_cliente", prices.getUuid_cliente());
		model.addAttribute("cer", prices.getCer());
		model.addAttribute("cer_descr", prices.getCer_descr());
		model.addAttribute("imponibile", prices.getImponibile());
		model.addAttribute("iva", prices.getIva());
		model.addAttribute("totale", prices.getTotale());
		model.addAttribute("nota_update", prices.getNota_update());
		model.addAttribute("last_update", prices.getTotale());
		
		int modified = 0;
		try {
			modified = pricesDao.modify(prices);
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

		if(modified == 0) {
			System.out.println("Si e' verificato un errore in fase di modifica del prezzo");
			model.addAttribute("message", "Si e' verificato un errore in fase di modifica del prezzo");
			
			return "error";
		}
		
		model.addAttribute("message", "Il prezzo e' stato modificato con successo");
		System.out.println("Il prezzo e' stato modificato con successo");
		return "result";
	}
	
	/**
	 * Delete price by CER
	 * 
	 * @param prices
	 * @param model
	 * @param uuid
	 */
	@RequestMapping(value = "/cancella-prezzo", method = {RequestMethod.POST, RequestMethod.GET}, params = "cer")
	public String deletePrice(@ModelAttribute("relationship-management") Prices prices, ModelMap model, @RequestParam String cer) {
		
		/* User Details */
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username", userDetails.getUsername());
		model.addAttribute("title", "Cancella Prezzo");
		
		try {
			pricesDao.delete(cer);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("message", "Il prezzo e' stato cancellato con successo");
		System.out.println("Il prezzo e' stato cancellato con successo");
		
		return "result";
	}
}
