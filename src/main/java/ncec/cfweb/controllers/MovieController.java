package ncec.cfweb.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import com.opencsv.CSVWriter;
import ncec.cfweb.Movie;
import ncec.cfweb.MovieForm;
import ncec.cfweb.MovieNameForm;
import ncec.cfweb.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

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
        model.addAttribute("duration", movie.getDuration()); // VYZH: todo: bad practice
        model.addAttribute("releasedate", movie.getDateCreation()); // VYZH: todo: bad practice
        model.addAttribute("directorname", "default");
        return "movieInfo";
//        return new ModelAndView("movieInfo", "movie", movie, );
    }

    @GetMapping(value = "/search-movie-page")
    String searchMoviePage(){
        return "searchMoviePage";
    }

    @PostMapping(value = "/search-movie-page")
    String searchMovie(Model model, @ModelAttribute("movieNameForm") MovieNameForm movieNameForm){
        //check by parse for movieName
        String mName = movieNameForm.getMovieName();
        List<Movie> movies = movieService.getByName(mName); // в будущем расширенный поиск и критерии
        //where is the checking must being?
        if (movies.isEmpty()){
            model.addAttribute("movieName", mName);
            return "searchMovieFailPage";
        } else{
            model.addAttribute("movies", movies);
            return "searchMovieResaultPage";
        }
    }

    @GetMapping(value = "/search-movie-fail-page{movieName}") //если в будущем не только имя?
    String searchMovieFailPage(Model model, @PathVariable(value = "movieName") String movieName){
        model.addAttribute("movieName", movieName);
        return "searchMovieFailPage";
    }

    //===========All Movies=====================
    
//    @GetMapping(value = "/allMovies")
//    ModelAndView allMovies(){
//        return new ModelAndView("allMovies", "movies", movieService.getAll());
//    }
    
    @GetMapping(value = "/allMovies")
    public String allMovies(Model model) {
 
        MovieNameForm movieNameForm = new MovieNameForm();
        model.addAttribute("movieNameForm", movieNameForm);
        model.addAttribute("movies", movieService.getAll());
 
        return "allMovies";
    }
    
    @PostMapping(value = "/allMovies")
    String allMoviesSearch(Model model,
//            @RequestParam(value="movieName") String movieName,
//            @RequestParam(value="date") Date date, // special form for to fit a date????
//            @RequestParam(value="duration") Integer duration,
//            @RequestParam(value="description") String description){
            @ModelAttribute("movieForm") MovieForm movieForm){
        //check by parse for movieName, date, duration and description
        
        if (!movieService.getByName(movieForm.getTitle()).isEmpty()){
            return "redirect: create-movie-fail-page?message=Фильм с таким именем существует!";
        }
        
        Movie movie = movieService.addMovie(
                movieForm.getTitle(),
                movieForm.getDateCreation(), // validation for Date!!!
                movieForm.getDuration(),
                movieForm.getDescription());
        model.addAttribute("movie", movie);
        return "redirect: allMovies";
    }
    
    //===========Create Movie=====================
    
    @GetMapping(value = "/create-movie-page")
    public String showCreateMoviePage(Model model) {
 
        MovieForm movieForm = new MovieForm();
        model.addAttribute("movieForm", movieForm);
 
        return "create-movie-page";
    }

    //cheking of the name of the film
    @PostMapping(value = "/create-movie-page")
    String createMovie(Model model,
//            @RequestParam(value="movieName") String movieName,
//            @RequestParam(value="date") Date date, // special form for to fit a date????
//            @RequestParam(value="duration") Integer duration,
//            @RequestParam(value="description") String description){
            @ModelAttribute("movieForm") MovieForm movieForm){
        //check by parse for movieName, date, duration and description
        
        if (!movieService.getByName(movieForm.getTitle()).isEmpty()){
            return "redirect: create-movie-fail-page?message=Фильм с таким именем существует!";
        }
        
        Movie movie = movieService.addMovie(
                movieForm.getTitle(),
                movieForm.getDateCreation(),
                movieForm.getDuration(),
                movieForm.getDescription());
        model.addAttribute("movie", movie);
        return "redirect: allMovies";
    }

    //addMovie независимо от поиска... при этом сервис должен проверить, нет ли такого фильма уже в наличии...

    //===========Edit Movie=====================

    // VYZH: todo: one method with GET mapping to draw the form
    @GetMapping(value = "/edit-movie-page")
    ModelAndView getEditMovie(@RequestParam Long id) {
        return new ModelAndView("editMoviePage", "movie", movieService.getById(id));
    }

    // VYZH: todo: one method with GET mapping to accept form submission
    @PostMapping(value = "/edit-movie-page")
    ModelAndView postEditMovie(
            @RequestParam(value="title") String movieName,
//            @RequestParam(value="date") Date date, // special form for to fit a date????
//            @RequestParam(value="duration") Integer duration,
//            @RequestParam(value="description") String description,
            @RequestParam(value="director_firstname") String directorFirstname,
            @RequestParam(value="director_lastname") String directorLastname
    ){
        //check by parse for movieName, date, duration and description
        Movie movie = movieService.editMovie(movieName, null, 0, "",
                directorFirstname, directorLastname);
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

    //============Export============= 

    @PostMapping("/export-allmovies")
    public ResponseEntity<StreamingResponseBody> exportAllMovies(@RequestParam List<Long> movieIds){
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"movies.csv\"")
                .body(out -> movieService.exportMovies(movieIds, out));
    }
    
    //============Import=============

    @PostMapping("/import-movie")
    ModelAndView importPage(@RequestParam String movieName){
        List<Movie> movies = movieService.importMovie(movieName);
        return new ModelAndView("import/result", "movies", movies);
    }
}
