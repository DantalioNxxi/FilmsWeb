package ncec.cfweb.services;

import java.io.File;
import java.util.Date;
import java.util.List;
import ncec.cfweb.Movie;

/**
 *
 * @author DantalioNxxi
 */

// service      : operation
// MovieService : create
// ServiceX     : get data external_source, then ....
public interface MovieService {
    
    Movie addMovie(Movie movie);
    
    Movie addMovie(String title, Date date, int duration, String description);
    
    void delete(String name);
    
    List<Movie> getByName(String name);
    
    Movie getById(Long id);
    
    Movie editMovie(Movie movie);//or by String name?
    
    List<Movie> getAll();
    
    File exportAllMovies(List<Movie> movies);
//    String exportAllMovies(List<Movie> movies);


    Movie editMovie(String movieName, Date date, Integer duration, String description, String directorFirstname, String directorLastname);

}
