package adp.realmng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView login(ModelMap model) {
	 
		System.out.println("Login Intercettato");
		
		ModelAndView modelandview = new ModelAndView();
		
		modelandview.setViewName("login/login_page");
		
		return modelandview;
	}
	 
	@RequestMapping(value="/loginError", method = RequestMethod.GET)
	public ModelAndView loginError(ModelMap model) {
		
		ModelAndView modelandview = new ModelAndView();
		//model.addAttribute("error", "true");
	
		modelandview.addObject("error", "Username e Password sono sbagliati. Prova a reinserirli.");
		modelandview.setViewName("login/login_page");
		
		return modelandview;
	}

	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public ModelAndView logout(ModelMap model) {
		
		ModelAndView modelandview = new ModelAndView();
		modelandview.addObject("msg", "Logout effettuato con successo.");		
		modelandview.setViewName("login/login_page");
		
		return modelandview;
	}
	
}
