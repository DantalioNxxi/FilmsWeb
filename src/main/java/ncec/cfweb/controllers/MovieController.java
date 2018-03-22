package ncec.cfweb.controllers;

import java.util.Date;
import java.util.List;
import ncec.cfweb.Movie;
import ncec.cfweb.services.MovieService;
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
@RequestMapping("/movie")
public class MovieController {
    
    @Autowired
    MovieService movieService;
    
    @RequestMapping(value = "/movieInfo/{movieId}", method = RequestMethod.GET)
    String movieInfo(Model model, 
        @PathVariable(value = "movieId") Integer movieId){
        Movie movie = movieService.getById(movieId);
        model.addAttribute("movie", movie);
        return "movieInfo";
    }
    
    @RequestMapping(value = "/search-movie-page", method = RequestMethod.GET)
    @ResponseBody
    String searchMoviePage(Model model){
        return "searchMoviePage";
    }
    
    @RequestMapping(value = "/search-movie-page", method = RequestMethod.POST)
    String searchMovie(Model model,
            @RequestParam(value="movieName") String movieName){
        //check by parse for movieName
        List<Movie> movies = movieService.getByName(movieName);
        //where is the checking must being?
        if (movies==null || movies.isEmpty()){
            return "searchMovieFailPage";
        }
        else{
            model.addAttribute("movies", movies);
            return "searchMovieResaultPage";
        }
    }
    
    @RequestMapping(value = "/search-movie-fail-page", method = RequestMethod.GET)
    @ResponseBody
    String searchMovieFailPage(Model model){
        return "searchMovieFailPage";
    }
    
    @RequestMapping(value = "/allMovies", method = RequestMethod.GET)
    @ResponseBody
    String allMovies(Model model){
        return "allMovies";
    }
    
    //..
    //
    
    @RequestMapping(value = "/create-movie-page", method = RequestMethod.POST)
    String createMovie(Model model,
            @RequestParam(value="movieName") String movieName,
            @RequestParam(value="date") Date date, // special form for to fit a date????
            @RequestParam(value="duration") Integer duration,
            @RequestParam(value="description") String description){
        //check by parse for movieName, date, duration and description
        Movie movie = movieService.addMovie(movieName, date, duration, description);
        model.addAttribute("movie", movie);
        return "movieInfo";
    }
    
    //addMovie независимо от поиска... при этом сервис должен проверить, нет ли такого фильма уже в наличии...
    
    //editMovie (взаимодействие с секьюрити (модератор, администратор, юзер, гость))
}
