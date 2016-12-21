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
import org.springframework.web.servlet.ModelAndView;

import adp.realmng.dao.GATransportMUDDaoImpl;
import adp.realmng.model.GATransportRecordView;

@Controller
public class GATransportMUDController {

	/* Logger */
	Logger logger = LoggerFactory.getLogger(GATransportMUDController.class);
	
	/* DAO configuration */
	Resource r=new ClassPathResource("WEB-INF/deployerConfigContext.xml");
	BeanFactory factory = new XmlBeanFactory(r);
	GATransportMUDDaoImpl dao = (GATransportMUDDaoImpl)factory.getBean("TransportMUDDao");
	
	@RequestMapping(value = "/trasporti-mud", method = RequestMethod.GET)
	public ModelAndView printHome(ModelMap model) {
		
		System.out.println("Trasporti Mud");
		
		ModelAndView modelAndView = new ModelAndView("trasporti-mud/trasporti-mud");
		
		modelAndView.addObject("title", "Sommatoria Trasporti MUD");
		modelAndView.addObject("message", "Inserisci tracciato trasporto");
		
		/* User Details */
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		modelAndView.addObject("username", userDetails.getUsername());
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/inserisci-trasporto", method = {RequestMethod.POST, RequestMethod.GET})
	public String addRecordTransport(@ModelAttribute("relationship-management")GATransportRecordView record, 
			ModelMap model) {

		model.addAttribute("codice_materiale", record.getCodice_materiale());
		model.addAttribute("azienda_destinazione", record.getAzienda_destinazione());
		model.addAttribute("quantita", record.getQuantita());
		model.addAttribute("azienda_provenienza", record.getAzienda_provenienza());
		model.addAttribute("data_inizio", record.getData_inizio());
		model.addAttribute("ora_inizio", record.getOra_inizio());
		model.addAttribute("data_fine", record.getData_fine());
		model.addAttribute("ora_fine", record.getOra_fine());
		model.addAttribute("nota", record.getNota());
		
		try {
			
			String uuid = dao.insertTransportRecord(record);
			
			record.setUuid(uuid);
			
			model.addAttribute("record", record);
			
			System.out.println("New Transport Record created with UUID");
			logger.info("New Transport Record created with UUID: "+uuid);
			
		} catch (FileNotFoundException e) {
			logger.error("File not found.", e);
		} catch (IOException e) {
			logger.error("IOException found", e);
		} catch (Exception e) {
			logger.error("Generic Exception found", e);
		}
		
		model.addAttribute("title", "Record Trasporto Inserito con successo");
		model.addAttribute("h2", "Le informazioni inserite sono:");
		model.addAttribute("message", "Le informazioni inserite sono:");		
		
		System.out.println("AGAIN PRINTING: ");
		System.out.println("codice_materiale: "+record.getCodice_materiale());
		System.out.println("azienda di destinazione: "+record.getAzienda_destinazione());
		System.out.println("quantita: "+record.getQuantita());
		System.out.println("azienda_provenienza: "+record.getAzienda_provenienza());
		System.out.println("data_inizio: "+record.getData_inizio());
		System.out.println("ora_inizio: "+record.getOra_inizio());
		System.out.println("data_fine: "+record.getData_fine());
		System.out.println("ora_fine: "+record.getOra_fine());
		System.out.println("nota: "+record.getNota());
		
		
		/* User Details */
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username", userDetails.getUsername());
		
		return "result";
	}
	
	@RequestMapping(value = "/trasporti", method = RequestMethod.GET)
	public String listTransports(@ModelAttribute("relationship-management") GATransportRecordView record, ModelMap model) {
		
		System.out.println("Lista Trasporti Effettuati");
		
		 List<Map<String, Object>> transports = new ArrayList<Map<String, Object>>();
		
		try {
			
			transports = dao.listAllTransports();
			
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
		
		model.addAttribute("list_trasports", transports);
		
		model.addAttribute("result", "OK");
		model.addAttribute("error", "Trasporti trovati con successo");
		
		model.addAttribute("message", "Lista Trasporti:");
		model.addAttribute("title", "Lista Trasporti Effettuati");
		
		/* User Details */
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username", userDetails.getUsername());
		
		return "trasporti-mud/lista-trasporti";
	}
	
}
