package adp.realmng.controller;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import adp.realmng.dao.InvoiceDaoImpl;
import adp.realmng.model.Customer;
import adp.realmng.model.Invoice;

public class InvoiceController {

	@RequestMapping(value = "/fattura", method = RequestMethod.GET)
	public ModelAndView printHome(ModelMap model) {

		System.out.println("Inserisci la nuova fattura");
		
		Resource r=new ClassPathResource("WEB-INF/deployerConfigContext.xml");

		BeanFactory factory=new XmlBeanFactory(r);

		InvoiceDaoImpl dao=(InvoiceDaoImpl)factory.getBean("InvoiceDao");

		return new ModelAndView("fattura", "command", new Customer());
	}
	
	/**
	 * 
	 * 	
	 * @param invoice
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/inserisci-fattura", method = RequestMethod.POST)
	public String addCustomer(@ModelAttribute("relationship-management")Invoice invoice, 
			ModelMap model) {
		System.out.println("Spring Inserisco Utente");
		
		/* Quando si inserisce una partita iva, bisogna controllare che l'utente sia inserito */
		System.out.println("uuid: "+invoice.getUuid());
		System.out.println("descrizione: "+invoice.getDescrizione());
		System.out.println("partita_iva: "+invoice.getPartita_iva());
		System.out.println("data_emissione: "+invoice.getData_emissione());
		
		model.addAttribute("uuid", invoice.getUuid());
		model.addAttribute("descrizione", invoice.getDescrizione());
		model.addAttribute("partita_iva", invoice.getPartita_iva());
		model.addAttribute("data_emissione", invoice.getData_emissione());

		return "result";
	}
	
}
