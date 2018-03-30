package ncec.cfweb.controllers;

import java.util.List;
import ncec.cfweb.Movie;
import ncec.cfweb.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author DantalioNxxi
 */
@Controller
@RequestMapping("/")
public class MainController {
    
    @Autowired
    MovieService movieService;
    
    @GetMapping()
    ModelAndView index(){
        return new ModelAndView("index");
    }
    
    @PostMapping(value = "/export-allmovies")
    String exportAllMovies(Model model){
        //check by parse for movieName
        List<Movie> movies = movieService.getAll(); // VYZH: todo: not all but selected entities
        //where is the checking must being?
        if (movies.isEmpty()){
            return "redirect:/export-allmovies/fail-page";
        } else{
            String pathToCSV = movieService.exportAllMovies(movies);
            model.addAttribute("countMovies", movies.size());
            model.addAttribute("pathToCSV", pathToCSV);
            return "successExportAllmovies";
        }
    }

    // VYZH: todo: move to MovieController
    // VYZH: todo: implement file download in the browser
    // https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-return-types
    // ResponseEntity + StreamingResponseBody
    @GetMapping(value = "/export-allmovies/fail-page")
    @ResponseBody
    String exportFailPage(){
        return "There are nothing for to export.";
    }
    
    
    
//    @GetMapping(value = "/export-allmovies/success-page")
//    @ResponseBody
//    String exportSuccessPage(){
//        return "There are nothing for to export.";
//    }
    //at future - button to create/add new film
    
    //
    
    
}
