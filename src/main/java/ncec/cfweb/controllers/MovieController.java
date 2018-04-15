package ncec.cfweb.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import ncec.cfweb.Movie;
import ncec.cfweb.Movies;
import ncec.cfweb.services.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author DantalioNxxi
 */
@Controller
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieService movieService;
    
    private static final Logger LOG = LoggerFactory.getLogger(MovieController.class);
    
    //===========Movie Info=====================

    @GetMapping(value = "/movie-info/{movieId}")
    ModelAndView movieInfo(Model model, @PathVariable(value = "movieId") Long movieId){
        ModelAndView mv = new ModelAndView("movie/movie-info");
        Movie movie = movieService.getById(movieId);
        mv.addObject("movie", movie);
        LOG.info("Movie Info: ");
        LOG.info("Is movie has persons??: "+movie.getPersons().toString());
        mv.addObject("persons", movie.getPersons());
        return mv;
    }
    
    @PostMapping(value = "/movie-info{movieId}")
    ModelAndView afterEditInfoMovie(@PathVariable(value = "movieId") Long movieId,
            @RequestParam(value="movieName") String movieName){
        //check by parse for movieName
        Movie movie = movieService.getById(movieId);        //where is the checking must being?
        return new ModelAndView("movie/movie-info", "movie", movie);
    }
    
    //===========Search Movies=====================

    @GetMapping(value = "/search-movie-page")
    String searchMoviePage(){
        return "movie/search-movie-page";
    }

    @PostMapping(value = "/search-movie-page")
    String searchMovie(Model model, @RequestParam String movieName){
        
        System.out.println("INTERNAL CONTROL");
        
        List<Movie> movies = movieService.getByName(movieName);
        
        if (movies.isEmpty()){
            model.addAttribute("movieName", movieName);
            return "movie/search-movie-miss-page";
        } else{
            model.addAttribute("movies", movies);
            return "movie/search-movie-result-page";
        }
    }

    @GetMapping(value = "/search-movie-miss-page{movieName}") //если в будущем не только имя?
    String searchMovieMissPage(Model model, @PathVariable(value = "movieName") String movieName){
        model.addAttribute("movieName", movieName);
        return "movie/search-movie-miss-page";
    }

    //===========All Movies=====================
    
    @GetMapping(value = "/all-movies")
    public ModelAndView allMovies(Model model) {
 
        ModelAndView mv = new ModelAndView("movie/all-movies");
        mv.addObject("movies", movieService.getAll());
 
        return mv;
    }
    
    @PostMapping(value = "/all-movies")
    String allMoviesSearch(Model model,
            @RequestParam(value="movieName") String title,
//            @RequestParam(value="date") Date date, // special form for to fit a date????
            @RequestParam(value="duration") Integer duration,
            @RequestParam(value="description") String description){
        //check by parse for movieName, date, duration and description
        
        if (!movieService.getByName(title).isEmpty()){
            return "redirect: create-movie-miss-page?message=Фильм с таким именем существует!";
        }
        
        movieService.addMovie(title, duration, description); //date, 
        
        return "redirect:movie/all-movies"; //movie/ was added
    }
    
    //============Import=============
    
    @PostMapping("/import-movie")
    RedirectView importPage(@RequestParam String movieName, RedirectAttributes redirectAttributes){
        ArrayList<Movie> movies = (ArrayList<Movie>) movieService.importMovie(movieName);//ver2
        redirectAttributes.addFlashAttribute("movies", movies);
        RedirectView redirectView = new RedirectView("/movie/import-movie-result");
        redirectView.getAttributesMap().put("movies", movies);
        return redirectView;
//        return "redirect:/all-movies";
//        return new ModelAndView("import/import-movie-page", "movies", movies);
    }
    
    @GetMapping(value = "/import-movie-result")
    ModelAndView importMovieResult(RedirectAttributes redirectAttributes){
        return new ModelAndView("import/import-movie-page");//.addObject("movies", movies)
    }
    
//    @PostMapping(value = "/import-movie-save")
//    ModelAndView importAfterSavePage(@RequestParam List<Integer> movieIds, RedirectAttributes redirectAttributes){
////        redirectAttributes.addAttribute("movies");
//
//        List<Movie> movies = (List<Movie>) redirectAttributes.getFlashAttributes().get("movies");
//        movieService.saveMovies(movies, movieIds);
//        return new ModelAndView("import/import-movie-save-page","quantityMovies", 2)//movieIds.size()
////                .addObject("movies", movieIds);//temporrary is four
//                .addObject("movies", movieService.getAll());//temporrary is four
//    }
//
//    @PostMapping(value = "/import-movie-save")
//    ModelAndView importAfterSavePage2(@RequestParam List<Integer> movieIds, RedirectAttributes flashMap){
//        List<Movie> movies = (List<Movie>) flashMap.getFlashAttributes().get("movies");
//        movieService.saveMovies(movies, movieIds);
//        return new ModelAndView("import/import-movie-save-page","quantityMovies", 2)//movieIds.size()
////                .addObject("movies", movieIds);//temporrary is four
//                .addObject("movies", movieService.getAll());//temporrary is four
//    }
    
    
    //===========Create Movie=====================
    
    @GetMapping(value = "/create-movie-page")
    public String showCreateMoviePage() {
        //...
        return "movie/create-movie-page";
    }

    //cheking of the name of the film
    @PostMapping(value = "/create-movie-page")
    String createMovie(@RequestParam(value="movieName") String movieName,
//            @RequestParam(value="dateCreation") Date date, // special form for to fit a date????
            @RequestParam(value="duration") Integer duration,
            @RequestParam(value="description") String description){
        //check by parse for movieName, date, duration and description...
        
        if (!movieService.getByName(movieName).isEmpty()){
            return "redirect: create-movie-miss-page?message=Фильм с таким именем существует!";
        }
        
        Movie movie = movieService.addMovie(movieName, duration, description);//date,
        //addMovie независимо от поиска... при этом сервис должен проверить, нет ли такого фильма уже в наличии...
        return "redirect: all-movies";
    }

    //===========Edit Movie=====================

    @GetMapping(value = "/edit-movie-page")
    ModelAndView getEditMovie(@RequestParam Long id) {
        return new ModelAndView("movie/edit-movie-page", "movie", movieService.getById(id));
    }

    @PostMapping(value = "/edit-movie-page")
    ModelAndView postEditMovie(
            @RequestParam(value="title") String movieName,
            @RequestParam(value="date") Date date, // special form for to fit a date????
            @RequestParam(value="duration") Integer duration,
            @RequestParam(value="description") String description,
            @RequestParam(value="director_firstname") String directorFirstname,
            @RequestParam(value="director_lastname") String directorLastname)
    {
        //check by parse for movieName, date, duration and description
        Movie movie = movieService.editMovie(movieName, date, duration, description, directorFirstname, directorLastname);
        //editMovie (взаимодействие с секьюрити (модератор, администратор, юзер, гость))
//        model.addAttribute("movie", movie);
        return new ModelAndView("movie/movie-info", "movie", movie);
    }
    

    //============Export============= 

    @PostMapping("/export-movies")
    public ResponseEntity<StreamingResponseBody> exportMovies(@RequestParam List<Long> movieIds){
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"movies.csv\"")
                .body(out -> movieService.exportMovies(movieIds, out));
    }
    
//    @GetMapping(value = "/export-movies-miss-page")
//    @ResponseBody
//    String exportMissPage(){
//        return "There are nothing for to export.";     //tm..............
//    }
    
    
    
}
