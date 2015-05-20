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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import adp.realmng.dao.WorksiteDaoImpl;
import adp.realmng.model.Customer;
import adp.realmng.model.Worksite;

@Controller
public class WorksiteController {

	/* Logger */
	Logger logger = LoggerFactory.getLogger(WorksiteController.class);
	
	/* DAO configuration */
	Resource r=new ClassPathResource("WEB-INF/deployerConfigContext.xml");
	BeanFactory factory = new XmlBeanFactory(r);
	WorksiteDaoImpl dao = (WorksiteDaoImpl)factory.getBean("WorksiteDao");
	
	@RequestMapping(value = "/cantiere", method = RequestMethod.GET)
	public ModelAndView printHome(ModelMap model) {
		
		ModelAndView modelAndView = new ModelAndView("cantiere/cantiere", "command", new Customer());
		modelAndView.addObject("title", "Inserisci Nuovo Cantiere");
		modelAndView.addObject("message", "Il Nuovo Cantiere");
		
		return modelAndView; 
	}
	
	@RequestMapping(value = "/inserisci-cantiere", method = RequestMethod.POST)
	public String addWorsite(@ModelAttribute("relationship-management")Worksite worksite, 
			ModelMap model) {
				
		System.out.println("Inserisco Cantiere");
		
		model.addAttribute("nome", worksite.getNome_cantiere());
		model.addAttribute("nota", worksite.getNota());
		model.addAttribute("indirizzo", worksite.getIndirizzo());
	    
		try {
			
			String uuid = dao.insert(worksite);
			
			System.out.println("New Worksite created with UUID");
			logger.info("New Worksite created with UUID: "+uuid);
			
			worksite.setUuid(uuid);
			
			model.addAttribute("worksite", worksite);
		} catch (Exception e) {
			logger.error("Generic Exception found", e);
		}
		
		model.addAttribute("title", "Cantiere Inserito con successo");
		model.addAttribute("h2", "Le informazioni del cantiere inserito sono:");
		model.addAttribute("message", "Le informazioni del cantiere inserito sono:");
		
		return "result";
	}
	
	//TODO Lista Cantieri
	//TODO Cancella Cantiere
	//TODO Modifica Cantiere
	
	
	@RequestMapping(value = "/gestisci-cantiere", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView gestisciCantiere(@RequestParam("uuid") String uuid, 
			ModelMap model) {
		
		System.out.println("Gestisci Cantiere");
		System.out.println("cantiere uuid: "+uuid);
		
		ModelAndView modelAndView = new ModelAndView("cantiere/gestisci-cantiere"); 
		modelAndView.addObject("title", "Gestisci Cantiere");
		Worksite worksite = dao.findByWorksiteUuid(uuid);
		
		modelAndView.addObject("cantiere", worksite);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/cantieri", method = RequestMethod.GET)
	public String listEmployers(ModelMap model) {
		
		System.out.println("Lista Cantieri");
		
		List<Map<String, Object>> worksites = new ArrayList<Map<String, Object>>();
		
		try {
			
			worksites = dao.listAllWorksites();
			
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
		
		model.addAttribute("list_worksites_by_end_date", worksites);
		
		model.addAttribute("result", "OK");
		model.addAttribute("error", "I cantieri aperti sono:");
		
		return "cantiere/lista-cantieri";		
	}
	
}
