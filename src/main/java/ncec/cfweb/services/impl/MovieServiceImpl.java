package ncec.cfweb.services.impl;

import com.opencsv.CSVWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import ncec.cfweb.Movie;
import ncec.cfweb.repositories.MovieRepository;
import ncec.cfweb.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DantalioNxxi
 */
@Service
public class MovieServiceImpl implements MovieService{

    @Autowired
    MovieRepository movieRepository;

    @Override
    public Movie addMovie(Movie movie) {//or use help from another service?
        //...
        //return movieRepository.save(movie);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Movie addMovie(String title, Date date, int duration, String description) {
        //cheking for having such film in db
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void delete(String name) {//still without checking!
//        movieRepository.delete(movieRepository.findByName(name));
        movieRepository.deleteByTitle(name);
    }

    @Override
    public List<Movie> getByName(String name) {
        return movieRepository.findByTitle(name);
    }

    @Override
    public Movie getById(Long id) {
        return movieRepository.findById(id);
    }
    
    @Override
    public Movie editMovie(Movie movie) {
        //...
        return movieRepository.save(movie);//newMovie
    }

    @Override
    public List<Movie> getAll() {
        return movieRepository.findAll();//is permit?
    }

    @Override
    public String exportAllMovies(List<Movie> movies) {
        //or export Object items in another service?
        try(CSVWriter writer
            = new CSVWriter(new BufferedWriter(new FileWriter("movies.csv")))){
            writer.writeNext(new String[]{"MOVIE_ID", "TITLE", "DURATION",
                "DIRECTOR_FIRSTNAME", "DIRECTOR_LASTNAME"});
            for (Movie movie : movies) {
                String[] line = formatMovieToLine(movie); // method to format properties of Participant with comma's
                writer.writeNext(line);
            }
//            writer.close();
        } catch (IOException ex) {
            //...later
        }
        return "movies.csv";
    }
    
    String [] formatMovieToLine(Movie movie){
        String [] str = new String[5];
        str[0] = Long.toString(movie.getId());
        str[1] = movie.getTitle();
        str[2] = Integer.toString(movie.getDuration());
        str[3] = movie.getDirector().getFirstname(); // VYZH: todo: NPE
        str[4] = movie.getDirector().getLastname(); // VYZH: todo: NPE
        return str;
    }
    
}
