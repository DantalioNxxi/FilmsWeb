package ncec.cfweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author DantalioNxxi
 */
@Controller
public class MainController {
    
    @RequestMapping(value = "/", method = RequestMethod.GET)//value = "/{message}" or GetMpping...
    @ResponseBody
    String index(Model model){
        //model.addAttribute("message", "Hello World!");
        return "index";
    }
    
    //
    
    
}
