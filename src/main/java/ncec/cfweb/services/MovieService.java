package ncec.cfweb.services;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import ncec.cfweb.Movie;

/**
 *
 * @author DantalioNxxi
 */

public interface MovieService {
    
    Movie addMovie(String title, int duration, String description);//Date date, 
    
    void delete(String name);
    
    List<Movie> getByName(String name);
    
    Movie getById(Long id);
    
    List<Movie> getAll();

    Movie editMovie(String movieName, Date date, Integer duration, String description, String directorFirstname, String directorLastname);

    List<Movie> getByIds(Collection<Long> movieIds);
    
    void exportMovies(List<Long> movieIds, OutputStream out) throws IOException;
    
    List<Movie> importMovie(String movieName);
    
    void saveMovies(List<Movie> movies, List<Integer> ids);
}
