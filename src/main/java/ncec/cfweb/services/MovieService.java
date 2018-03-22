package ncec.cfweb.services;

import java.util.Date;
import java.util.List;
import ncec.cfweb.Movie;

/**
 *
 * @author DantalioNxxi
 */
public interface MovieService {
    
    Movie addMovie(Movie movie);
    
    Movie addMovie(String title, Date date, int duration, String description);
    
    Movie createMovie();//обращение к стороннему сервису?
    
    void delete(String name);
    
    List<Movie> getByName(String name);
    
    Movie getById(Integer id);
    
    Movie editMovie(Movie movie);//or by String name?
    
    List<Movie> getAll();
    
    
    
    
    
}
