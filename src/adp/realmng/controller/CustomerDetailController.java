package adp.realmng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

@Controller
@RequestMapping("/dettaglio-cliente")
public class CustomerDetailController{
 
   @RequestMapping(method = RequestMethod.GET)
   public String printHome(ModelMap model) {
      model.addAttribute("message", "Dettaglio Cliente");

      return "dettaglio-cliente";
   }

}
