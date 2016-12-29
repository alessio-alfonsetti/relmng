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
import adp.realmng.model.Customer;
import adp.realmng.model.GATransportRecordView;
import adp.realmng.model.Invoice;
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
				System.out.println("L'utente non ha un listino prezzi. Crearne uno.");
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
	
	@RequestMapping(value = "/listino", method = {RequestMethod.GET, RequestMethod.POST}, params = {"uuid", "ragione_sociale", "lastname"})
	public ModelAndView printHome(ModelMap model, 
			@RequestParam("uuid") String uuid, @RequestParam("ragione_sociale") String ragione_sociale, @RequestParam("lastname") String lastname) {
		
		ModelAndView modelAndView = new ModelAndView("listino/inserisci-listino", "command", new Prices()); 
		
		modelAndView.addObject("title", "Inserisci Nuovo Prezzo nel Listino");
		modelAndView.addObject("message", "Inserisci Nuovo Prezzo nel Listino");
		
		String ragSoc_Cogn = null;
		if(ragione_sociale != null && ragione_sociale != "") {
			modelAndView.addObject("rag_soc_cogn", ragione_sociale);
			ragSoc_Cogn = ragione_sociale;
		}
		else {
			modelAndView.addObject("rag_soc_cogn", lastname);
			ragSoc_Cogn = lastname;
		}
		
		System.out.println("client uuid: "+uuid);
		
		try {
			List<Map<String,Object>> prices = pricesDao.findPricesByClientUuid(uuid);
			System.out.println("Prezzi associati al cliente: "+prices.size());
			
			if(prices != null)
				modelAndView.addObject("client_prices", prices);
			
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
		
		/* User Details */
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		modelAndView.addObject("username", userDetails.getUsername());
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/inserisci-listino", method = {RequestMethod.POST, RequestMethod.GET})
	public String addInListino(@ModelAttribute("relationship-management")Prices prices, ModelMap model) {
		
		model.addAttribute("cer", prices.getCer());
		model.addAttribute("cer_descr", prices.getCer_descr());
		model.addAttribute("imponibile", prices.getImponibile());
		model.addAttribute("iva", prices.getIva());
		model.addAttribute("totale", prices.getTotale());
		System.out.println("uuid_cliente: "+prices.getUuid_cliente());
		
		try {
			String uuid_cliente = customerDao.findByRagSocCogn(prices.getUuid_cliente());
			model.addAttribute("uuid_cliente", uuid_cliente);
			prices.setUuid_cliente(uuid_cliente);
			
			pricesDao.insert(prices);
			
			model.addAttribute("last_update", prices.getLast_update());
			model.addAttribute("nota_update", prices.getNota_update());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("prices", prices);
		model.addAttribute("title", "Prezzo aggiungo con successo nel Listino");
		model.addAttribute("h2", "Le informazioni inserite sono:");
		model.addAttribute("message", "Le informazioni inserite sono:");		
		
		/* User Details */
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username", userDetails.getUsername());
		
		return "result";
	}
	
}
