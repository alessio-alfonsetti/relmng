package adp.realmng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

@Controller
@RequestMapping("/prodotto")
public class ProductController{
 
   @RequestMapping(method = RequestMethod.GET)
   public String printHome(ModelMap model) {
      model.addAttribute("message", "Inserisci Nuovo Prodotto");

      return "prodotto";
   }

}
