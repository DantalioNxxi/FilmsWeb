package ncec.cfweb.services;

import java.util.List;
import ncec.cfweb.Movie;

/**
 *
 * @author DantalioNxxi
 */
public interface MovieService {
    
    Movie addMovie(Movie movie);
    
    Movie createMovie();//обращение к стороннему сервису?
    
    void delete(String name);
    
    Movie getByName(String name);
    
    Movie editMovie(Movie movie);//or by String name?
    
    List<Movie> getAll();
    
    
    
    
    
}
