package adp.realmng.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
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
import org.springframework.web.servlet.ModelAndView;

import adp.realmng.dao.CustomerDaoImpl;

@Controller
public class HomeController {
	
	/**
	 * Setting the context to get connection parameters.
	 * @author Alfonsetti
	 * */
	private static final BeanFactory factory = new XmlBeanFactory(new ClassPathResource("../deployerConfigContext.xml"));
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView getdata() {
 
		ModelAndView model = new ModelAndView("home");
		return model;
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String printHome(ModelMap model) {
	   
	   System.out.println("home page controller");

		model.addAttribute("message", "Notifiche");

		Resource r=new ClassPathResource("WEB-INF/deployerConfigContext.xml");

		BeanFactory factory=new XmlBeanFactory(r);

		CustomerDaoImpl dao=(CustomerDaoImpl)factory.getBean("CustomerDao");

		List<Map<String, Object>> customers = null;

		try {
			customers = dao.listCustomersForNotification();
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
		
		model.addAttribute("list_customers_for_notifcation", customers);
	   
	   return "home";
   }

}
