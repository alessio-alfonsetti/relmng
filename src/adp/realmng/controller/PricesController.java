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
	
	@RequestMapping(value = "/listino-prezzi", method = RequestMethod.GET)
	public String listPrices(@ModelAttribute("relationship-management") Prices record, ModelMap model) {
		
		System.out.println("Lista Prezzi");
		
		List<Map<String, Object>> customers = new ArrayList<Map<String, Object>>();
		
		try {
			
			customers = customerDao.listAllCustomers();
			
			
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
		 
		//model.addAttribute("list_trasports", transports);
		
		model.addAttribute("result", "OK");
		model.addAttribute("error", "Prezzi trovati con successo");
		
		model.addAttribute("message", "Lista Prezzi per Cliente:");
		model.addAttribute("title", "Lista Prezzi per Cliente");
		
		return "listino/prezzo-cliente";
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
			prices = pricesDao.findPricesByClientId(customer.getUuid());
			
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
	
	@RequestMapping(value = "/listino", method = {RequestMethod.GET, RequestMethod.POST}, params = "uuid")
	public ModelAndView printHome(ModelMap model, @RequestParam("uuid") String uuid) {
		
		ModelAndView modelAndView = new ModelAndView("listino/inserisci-listino", "command", new Prices()); 
		
		modelAndView.addObject("title", "Inserisci Nuovo Prezzo nel Listino");
		modelAndView.addObject("message", "Inserisci Nuovo Prezzo nel Listino");
		
		System.out.println("client uuid: "+uuid);
		
		try {
			pricesDao.findPricesByClientId(uuid);
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
		model.addAttribute("descr_cer", prices.getCer_descr());
		model.addAttribute("cliente", prices.getUuid_cliente());
		model.addAttribute("imponibile", prices.getImponibile());
		model.addAttribute("iva", prices.getIva());
		model.addAttribute("totale", prices.getTotale());
		System.out.println("cliente: "+prices.getUuid_cliente());
		try {
			//customerDao.findByRagSocCogn();
			
			pricesDao.insert(prices);
			
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
