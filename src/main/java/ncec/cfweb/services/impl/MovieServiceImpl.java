package ncec.cfweb.services.impl;

import java.util.Date;
import java.util.List;
import ncec.cfweb.Movie;
import ncec.cfweb.repositories.MovieRepository;
import ncec.cfweb.services.MovieService;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

/**
 *
 * @author DantalioNxxi
 */
@Configuration
@Service
public class MovieServiceImpl implements MovieService{
    
    MovieRepository movieRepository;

    @Override
    public Movie addMovie(Movie movie) {//or use help from another service?
        //...
        //return movieRepository.save(movie);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Movie addMovie(String title, Date date, int duration, String description) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    @Override
    public Movie createMovie() {
        //another service?
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String name) {//still without checking!
        movieRepository.delete(movieRepository.findByName(name));
    }

    @Override
    public List<Movie> getByName(String name) {
        return movieRepository.findByName(name);
    }

    @Override
    public Movie getById(Integer id) {
        return movieRepository.findById(id);
    }
    
    @Override
    public Movie editMovie(Movie movie) {
        //...
        return movieRepository.save(movie);//newMovie
    }

    @Override
    public List<Movie> getAll() {
        return (List<Movie>)movieRepository.findAll();//is permit?
    }
    
    
    
    
    
}
