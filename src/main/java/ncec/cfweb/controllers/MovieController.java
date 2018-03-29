package ncec.cfweb.controllers;

import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author DantalioNxxi
 */
@Controller
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping(value = "/movieInfo/{movieId}")
    String movieInfo(Model model, @PathVariable(value = "movieId") Long movieId){
        Movie movie = movieService.getById(movieId);
        model.addAttribute("movie", movie);
        model.addAttribute("duration", movie.getDuration());
        model.addAttribute("releasedate", movie.getDateCreation());
        model.addAttribute("directorname", "default");
        return "movieInfo";
//        return new ModelAndView("movieInfo", "movie", movie, );
    }

    @GetMapping(value = "/search-movie-page")
    String searchMoviePage(){
        return "searchMoviePage";
    }

    @PostMapping(value = "/search-movie-page")
    String searchMovie(Model model, @RequestParam(value="movieName") String movieName){
        //check by parse for movieName
        List<Movie> movies = movieService.getByName(movieName);
        //where is the checking must being?
        if (movies.isEmpty()){
            return "searchMovieFailPage";
        } else{
            model.addAttribute("movies", movies);
            return "searchMovieResaultPage";
        }
    }

    @RequestMapping(value = "/search-movie-fail-page", method = RequestMethod.GET)
    String searchMovieFailPage(){
        return "searchMovieFailPage";
    }

    @GetMapping(value = "/allMovies")
    ModelAndView allMovies(){
        return new ModelAndView("allMovies", "movies", movieService.getAll());
    }

    //..
    //

    @PostMapping(value = "/create-movie-page")
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

    @PostMapping(value = "/edit-movie-page")
    ModelAndView editMovie(
            @RequestParam(value="movieName") String movieName,
            @RequestParam(value="date") Date date, // special form for to fit a date????
            @RequestParam(value="duration") Integer duration,
            @RequestParam(value="description") String description){
        //check by parse for movieName, date, duration and description
        Movie movie = movieService.addMovie(movieName, date, duration, description);
//        model.addAttribute("movie", movie);
        return new ModelAndView("movieInfo", "movie", movie);
    }
    //editMovie (взаимодействие с секьюрити (модератор, администратор, юзер, гость))
    
    @PostMapping(value = "/movieInfo{movieId}")
    ModelAndView afterEditInfoMovie(@PathVariable(value = "movieId") Long movieId,
            @RequestParam(value="movieName") String movieName){
        //check by parse for movieName
        Movie movie = movieService.getById(movieId);        //where is the checking must being?
        return new ModelAndView("movieInfo", "movie", movie);
    }
}
