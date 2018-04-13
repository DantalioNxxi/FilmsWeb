package ncec.cfweb.services.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collection;
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
import com.opencsv.CSVWriter;
import java.util.ArrayList;
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
    public Movie addMovie(String title, Date date, int duration, String description) {
        //cheking for having such film in db
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setDateCreation(date);
        movie.setDuration(duration);
        movie.setDescription(description);
        return movieRepository.save(movie);
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
    public List<Movie> getAll() {
        return movieRepository.findAll();//is permit?
    }

    
    //исправить на update!
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
    
    // VYZH: todo: 1) return File/Stream/etc -OR- 2) add OutputStream in parameters
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
        
        //for checking after xsl-handle:
        try(FileWriter fw = new FileWriter("afterXsltForShow.txt", false)){
              fw.write(afterXslt.toString());
        } catch (IOException ex){
            throw new IllegalStateException(ex);
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
//        return (List<Movie>) movieRepository.save(movies.getMovies());

//        System.out.println("Получем ids");
//        List<Movie> retMovies = movies.getMovies();
        ArrayList<Movie> retMovies = (ArrayList<Movie>)movies.getMovies();//ver2
        System.out.println("Сперва выведем названия фильмов:");
        System.out.println(retMovies.get(2).getTitle());
        System.out.println(retMovies.get(3).getTitle());
        System.out.println(retMovies.get(4).getTitle());
//        retMovies = moviesGetId(retMovies);
        
//        System.out.println("Пробуем вывести ids:\n");
//        for (Movie movie : retMovies){
//            System.out.println(movie.getId());
            
//        System.out.println("\nПросто выведем этот лист:");
//        for (Movie movie : retMovies){
//            System.out.println(movie.toString());
//        }

        return retMovies;
//        return movies.getMovies();
    }
    
    private List<Movie> moviesGetId(List<Movie> movies){//may be with help os Stream API?
        long i = 1;
        
        for (Movie movie : movies){
            movie.setId(i);
            System.out.println("i="+i);
            i++;
        }
        return movies;
    }
    
    @Override
    public void saveMovies(List<Movie> movies, List<Integer> ids) {
        //still there is nothing for return
//        for (Movie movie : movies){
//            System.out.println(movie.toString());
//        }
        
//        movieRepository.save(movies);

        for (Integer i : ids){
            Movie movie = movies.get(i);
            if (movieRepository.findByTitle(movie.getTitle()).isEmpty()){
                System.out.println("Выводим фильм под индексом: "+i);
                System.out.println(movies.get(i).getClass().getSimpleName());//ver2 - title
    //            movieRepository.save(movies.get(i));

                //parse director
                String[] str = movie.getFullname().split(" ");
                if (personService.getByFirstAndLastName(str[0], str[1]).isEmpty()){
                    Person person = new Person(str[0], str[1]);
                    movie.setDirector(personService.addPerson(person));
                } else {
                    movie.setDirector(personService.getByFirstAndLastName(str[0], str[1]).get(0));//tm
                }

                //parse date
                Integer date = Integer.parseInt(parseDateCreation(movie.getFulldate()));
//                movie.setDateCreation(date);//or made to Integer?
            }
            
        }
        
        
    }
    
    private String parseDateCreation(String fulldate){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fulldate.length(); i++){
            if (Character.isDigit(fulldate.charAt(i))){
                int elem = Character.digit(fulldate.charAt(i), 10);
                sb.append(elem);
            }
        }
        return sb.toString();
    }

    //END of class
}
