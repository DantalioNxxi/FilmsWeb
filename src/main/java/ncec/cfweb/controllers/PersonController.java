package ncec.cfweb.controllers;

import java.util.List;
import ncec.cfweb.Person;
import ncec.cfweb.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author DantalioNxxi
 */
@Controller
@RequestMapping("/person")
public class PersonController {
    
    @Autowired
    PersonService personService;
    
    @RequestMapping(value = "/personInfo/{personId}", method = RequestMethod.GET)
    String personInfo(Model model, 
        @PathVariable(value = "personId") Long personId){ //Long type is permit here?
        Person person = personService.getById(personId);
        model.addAttribute("person", person);
        return "personInfo";
    }
    
    @RequestMapping(value = "/search-person-page", method = RequestMethod.GET)
    @ResponseBody
    String searchPersonPage(Model model){
        return "searchPersonPage";
    }
    
    @RequestMapping(value = "/search-person-page", method = RequestMethod.POST)
    String searchPerson(Model model,
            @RequestParam(value="firstname") String firstname,
            @RequestParam(value="lastname") String lastname){
        //check by parse for movieName
        List<Person> persons = personService.getByFirstAndLastName(firstname, lastname);
        //where is the checking must being?
        if (persons==null || persons.isEmpty()){
            return "searchPersonFailPage";
        }
        else{
            model.addAttribute("persons", persons);
            return "searchPersonResaultPage";
        }
    }
    
    @RequestMapping(value = "/search-person-fail-page", method = RequestMethod.GET)
    @ResponseBody
    String searchMovieFailPage(Model model){
        return "searchPersonFailPage";
    }
    
    
    
    
    
    
    
    @RequestMapping(value = "/allPersons", method = RequestMethod.GET)
    @ResponseBody
    String allPersons(Model model){
        return "allPersons";
    }
    
}
