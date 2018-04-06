package ncec.cfweb.services.impl;

import com.opencsv.CSVWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import ncec.cfweb.Movie;
import ncec.cfweb.Movies;
import ncec.cfweb.Person;
import ncec.cfweb.repositories.MovieRepository;
import ncec.cfweb.services.MovieService;
import ncec.cfweb.services.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.tidy.Tidy;

import javax.xml.bind.JAXB;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author DantalioNxxi
 */
@Service
public class MovieServiceImpl implements MovieService{

    private static final Logger LOG = LoggerFactory.getLogger(MovieServiceImpl.class);
    @Autowired
    MovieRepository movieRepository;

    @Autowired
    PersonService personService;

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
    public void exportMovies(List<Long> movieIds, OutputStream out) throws IOException {
        List<Movie> movies = getByIds(movieIds);
        try (CSVWriter csv = new CSVWriter(new OutputStreamWriter(out))) {
            String[] line = new String[3];
            for (Movie movie : movies) {
                line[0] =  movie.getTitle();
                line[1] =  Integer.toString(movie.getDuration());
                line[2] =  movie.getDescription();
                csv.writeNext(line, true);
            }
        }
    }



    // VYZH: todo: 1) return File/Stream/etc -OR- 2) add OutputStream in parameters
    @Override
    public File exportAllMovies(List<Movie> movies) {
        //or export Object items in another service?
        try(CSVWriter writer
            = new CSVWriter(new BufferedWriter(new FileWriter("/movies.csv")))){
            writer.writeNext(new String[]{"MOVIE_ID", "TITLE", "DURATION",
                "DIRECTOR_FIRSTNAME", "DIRECTOR_LASTNAME"});
            for (Movie movie : movies) {
                String[] line = formatMovieToLine(movie); // method to format properties of Participant with comma's
                writer.writeNext(line);
            }
//            writer.close();
        } catch (IOException ex) {
//            return new File("/movies.csv");
            //...later
        }
        return new File("/movies.csv");
    }

    @Override
    public Movie editMovie(String movieName, Date date, Integer duration, String description, String directorFirstname, String directorLastname) {
        List<Movie> movies = movieRepository.findByTitle(movieName);
        if (movies.isEmpty()) {
            throw new IllegalArgumentException("Movie not found");
        }

        if (movies.size() > 1) {
            throw new IllegalArgumentException("Several movies exist with specified name");
        }

        Movie movie = movies.get(0);
        movie.setDateCreation(date);
        movie.setDuration(duration);
        List<Person> personList = personService.getByFirstAndLastName(directorFirstname, directorLastname);

        if (personList.isEmpty()) {
            throw new IllegalArgumentException("Person not found");
        }
        if (personList.size() > 1) {
            throw new IllegalArgumentException("Several people exist with specified name");
        }

        movie.setDirector(personList.get(0));
        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> getByIds(Collection<Long> movieIds) {
        return (List<Movie>) movieRepository.findAll(movieIds);
    }

    @Override
    public List<Movie> importMovie(String movieName) {
        // http request
        RestTemplate restTemplate = new RestTemplate();
        String html = restTemplate
                .getForObject("http://www.imdb.com/search/title?title={0}&title_type=feature,tv_movie,tv_series",
                        String.class, movieName);

        // sanitize JTidy
        Tidy tidy = new Tidy();
        tidy.setXmlOut(true);
        tidy.setQuiet(true);
        tidy.setShowWarnings(false);
        tidy.setNumEntities(true);
        tidy.setForceOutput(true);

        StringWriter xml = new StringWriter();
        tidy.parse(new StringReader(html), xml);

        LOG.debug("xml = {}", xml.toString());

        // XSLT
        StringWriter afterXslt = new StringWriter();
        try {
            InputStream xsl = getClass().getResourceAsStream("/import.xsl");
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer(new StreamSource(xsl));
            transformer.transform(new StreamSource(new StringReader(xml.toString())), new StreamResult(afterXslt));
        } catch (TransformerConfigurationException e) {
            throw new IllegalStateException(e);
        } catch (TransformerException e) {
            throw new IllegalStateException(e);
        }

        LOG.debug("afterXslt = {}", afterXslt.toString());

        // Movie
        Movies movies = JAXB.unmarshal(new StringReader(afterXslt.toString()), Movies.class);

        // save Repository
        // return

        // todo:
        // 1) проверить дублирование названий фильмов, убрать, сделать название на английском
        // 2) выбор фильмов подлежащих импорту, импортировать не все сразу
        // 3) добавить параметры - год, жанры, режисер, актеры
        return (List<Movie>) movieRepository.save(movies.getMovies());
    }

    // VYZH: todo: consider StatefulBeanToCsv, writer.writeNext(String[]) is suboptimal
    String [] formatMovieToLine(Movie movie){
        String [] str = new String[5];
        str[0] = Long.toString(movie.getId());
        str[1] = movie.getTitle();
        str[2] = Integer.toString(movie.getDuration());
        str[3] = "dirFirstName"; // VYZH: todo: NPE
        str[4] = "dirLastName"; // VYZH: todo: NPE
//        str[3] = movie.getDirector().getFirstname(); // VYZH: todo: NPE
//        str[4] = movie.getDirector().getLastname(); // VYZH: todo: NPE
        return str;
    }
    
}
