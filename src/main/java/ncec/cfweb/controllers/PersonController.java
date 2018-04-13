package ncec.cfweb.controllers;

import java.util.List;
import ncec.cfweb.Gender;
import ncec.cfweb.Person;
import ncec.cfweb.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author DantalioNxxi
 */
@Controller
@RequestMapping("/person")
public class PersonController {
    
    @Autowired
    PersonService personService;

    // #1
//    @GetMapping("/personInfo/{lastname}/{firstname}")

    // #2
//    @GetMapping("/personInfo") + @RequestParam String lastname, @RequestParam String firstname


    
    //===========All Persons=====================
    
    @GetMapping(value = "/all-persons")
    public ModelAndView allPersons(Model model){
        
        ModelAndView mv = new ModelAndView("person/all-persons");
        mv.addObject("persons", personService.getAll());
        
        return mv;
    }
    
    //===========Person Info=====================
    
    @GetMapping(value = "/person-info")
    String personInfo(Model model, 
        @RequestParam(value = "lastname") String lastname,
        @RequestParam(value = "firstname") String firstname){
//        Person person = personService.getById(personId);
        Person person = new Person(Gender.MALE, "vasya", "pupkin", 51); //temporary!
        personService.addPerson(person); //temporary!
        model.addAttribute("person", personService.getByFirstAndLastName(firstname, lastname).get(0));//[0] temporary
        return "person/person-info";
    }
    
    //===========Search Persons=====================
    
    @RequestMapping(value = "/search-person-page", method = RequestMethod.GET)
    @ResponseBody
    String searchPersonPage(Model model){
        return "person/search-person-page";
    }
    
    @RequestMapping(value = "/search-person-page", method = RequestMethod.POST)
    String searchPerson(Model model,
            @RequestParam(value="firstname") String firstname,
            @RequestParam(value="lastname") String lastname){
        //check by parse for movieName
        List<Person> persons = personService.getByFirstAndLastName(firstname, lastname);
        //where is the checking must being?
        if (persons==null || persons.isEmpty()){
            return "person/search-person-fail-page";
        }
        else{
            model.addAttribute("persons", persons);
            return "person/search-person-resault-page";
        }
    }
    
    @RequestMapping(value = "/search-person-fail-page", method = RequestMethod.GET)
    @ResponseBody
    String searchMovieFailPage(Model model){
        return "person/search-person-fail-page";
    }
    
    //===========Edit Person=====================
    
    @GetMapping(value = "/edit-person-page")
    ModelAndView getEditPerson(
        @RequestParam(value = "lastname") String lastname,
        @RequestParam(value = "firstname") String firstname){
        return new ModelAndView("person/edit-person-page", "person",
                personService.getByFirstAndLastName(firstname, lastname).get(0));
    }

    @PostMapping(value = "/edit-person-page")
    ModelAndView postEditPerson(
            @RequestParam(value="firstname") String firstname, //requierd??? to th:action -s working
            @RequestParam(value="lastname") String lastname,
            @RequestParam(value="age") int age)
    {
        //check by parse for movieName, date, duration and description
        Person person = personService.editPerson(firstname, lastname, age);
        return new ModelAndView("person/person-info", "firstname", firstname)
                .addObject("lastname", lastname);
    }
    
    //===========Create Person=====================
    
    @GetMapping(value = "/create-person-page")
    ModelAndView createPersonPage(){
        
//        PersonForm personForm = new PersonForm();
        return new ModelAndView("person/create-person-page");
    }
    
    @PostMapping(value = "/create-person-page")
            @ResponseBody
    String createPerson(@RequestParam(value="firstname") String firstname,
            @RequestParam(value="lastname") String lastname,
            @RequestParam(value="age") Integer age,
            @RequestParam(value="age") String gender) //tm
    {
        System.out.println("Внутри create person post controller: ");
        System.out.println("fname = "+firstname);
        System.out.println("lname = "+lastname);
        System.out.println("age = "+age);
        System.out.println("gender = "+gender);
        return "Создана персона: ...не реализовано создание!";
    }
    
}
