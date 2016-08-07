package adp.realmng.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import adp.realmng.dao.GATransportMUDDaoImpl;
import adp.realmng.model.Customer;
import adp.realmng.model.GATransportRecordView;

public class GATransportMUDController {

	/* Logger */
	Logger logger = LoggerFactory.getLogger(GATransportMUDController.class);
	
	/* DAO configuration */
	Resource r=new ClassPathResource("WEB-INF/deployerConfigContext.xml");
	BeanFactory factory = new XmlBeanFactory(r);
	GATransportMUDDaoImpl dao = (GATransportMUDDaoImpl)factory.getBean("TransportMUDDao");
	
	@RequestMapping(value = "/trasporti-mud", method = RequestMethod.GET)
	public ModelAndView printHome(ModelMap model) {
		
		ModelAndView modelAndView = new ModelAndView("trasporti-mud/trasporti-mud", "command", new Customer()); 
		
		modelAndView.addObject("title", "Sommatoria Trasporti MUD");
		modelAndView.addObject("message", "Inserisci tracciato trasporto");
		
		/* User Details */
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		modelAndView.addObject("username", userDetails.getUsername());
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/inserisci-trasporto", method = {RequestMethod.POST, RequestMethod.GET})
	public String addRecordTransport(@ModelAttribute("relationship-management")GATransportRecordView customer, 
			ModelMap model) {
//		System.out.println("Spring Inserisco Record Trasporto");
//		System.out.println("ragione_sociale: "+customer.getRagione_sociale());
//		System.out.println("nome: "+customer.getFirstname());
//		System.out.println("cognome: "+customer.getLastname());
//		System.out.println("email: "+customer.getEmail());
//		System.out.println("partita_iva: "+customer.getPartita_iva());
//		System.out.println("codice_fiscale: "+customer.getCodice_fiscale());
//		System.out.println("nota: "+customer.getNota());
//		System.out.println("numero_cellulare: "+customer.getNumero_cellulare());
//		System.out.println("indirizzo: "+customer.getIndirizzo());
//		
//		model.addAttribute("ragione_sociale", customer.getRagione_sociale());
//		model.addAttribute("nome", customer.getFirstname());
//		model.addAttribute("cognome", customer.getLastname());
//		model.addAttribute("email", customer.getEmail());
//		model.addAttribute("codice_fiscale", customer.getCodice_fiscale());
//		model.addAttribute("partita_iva", customer.getPartita_iva());
//		model.addAttribute("codice_fiscale", customer.getCodice_fiscale());
//		model.addAttribute("nota", customer.getNota());
//		model.addAttribute("numero_cellulare", customer.getNumero_cellulare());
//		model.addAttribute("iban", customer.getIban());
//		model.addAttribute("indirizzo", customer.getIndirizzo());
//		
//		try {
//			
//			String uuid = dao.insert(customer);
//			
//			customer.setUuid(uuid);
//			
//			System.out.println("New Transport Record created with UUID");
//			logger.info("New Transport Record created with UUID: "+uuid);
//			
//		} catch (FileNotFoundException e) {
//			logger.error("File not found.", e);
//		} catch (IOException e) {
//			logger.error("IOException found", e);
//		} catch (Exception e) {
//			logger.error("Generic Exception found", e);
//		}
		
		model.addAttribute("title", "Record Trasporto Inserito con successo");
		model.addAttribute("h2", "Le informazioni inserite sono:");
		model.addAttribute("message", "Le informazioni inserite sono:");		
		
		/* User Details */
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username", userDetails.getUsername());
		
		return "result";
	}
	
}
