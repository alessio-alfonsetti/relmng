package adp.realmng.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import adp.realmng.dao.CustomerDaoImpl;
import adp.realmng.model.Customer;

@Controller
@RequestMapping("/lista-clienti")
public class CustomerListController {

	@RequestMapping(method = RequestMethod.GET)
	public String printHome(ModelMap model) {

		System.out.println("printHome");

		model.addAttribute("message", "Lista di Clienti");

		Resource r=new ClassPathResource("WEB-INF/deployerConfigContext.xml");

		BeanFactory factory=new XmlBeanFactory(r);

		CustomerDaoImpl dao=(CustomerDaoImpl)factory.getBean("CustomerDao");

		List<Map<String, Object>> customers = null;

		try {
			customers = dao.listAllCustomers();
		} catch (FileNotFoundException e) {

			System.out.println("1");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {

			System.out.println("2");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {

			System.out.println("3");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("customers: "+customers);

		//TODO Parsing the "customers" list retrieved by the database and return the first ten  to the View
		model.addAttribute("list_customers", customers);

		return "lista-clienti";
	}
	
}
