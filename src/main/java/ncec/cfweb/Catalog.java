package ncec.cfweb;

import ncec.cfweb.repositories.CatalogManager;
import java.util.HashMap;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.springframework.data.annotation.Id;

/**
 *
 * @author DantalioNxxi
 */
@Entity
public class Catalog {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    String name;
    
    //need it?
    FabricMovies fm;
    
    //in generally, is it need? then optional i TRUE??????
    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private User creator;
    
    @OneToMany(fetch = FetchType.EAGER)//?????????????
    Set<Movie> films;//!!!!!!!! do methods do not need more?
    //or simply set?
//    HashMap<String, Movie> films;
    
    //HashSet<Movie> films;

    protected Catalog() {
    }
    
    public Catalog(String name) {
        this.name = name;
    }

    public Catalog(User creator, String name) {
        this.creator = creator;
        this.name = name;
    }
    
//    public Movie getMovie(String name){
//        Movie need;
//        if(!films.containsKey(name)){
//            need = fm.getMovie(name);
//        } else need = films.get(name);
//        return need;
//    }
//    
//    public void addMovie(Movie movie)throws CatalogException{ //and etc
//        if(films.containsKey(movie.getTitle())) throw new CatalogException("Фильм с таким именем уже есть в каталоге");
//        if(films.containsValue(movie)) throw new CatalogException("Такой фильм уже есть в каталоге");
//        
//        //if it is this new added film
//        if(!CatalogManager.movies.containsValue(movie)){
//            CatalogManager.addMovie(movie);
//        }
//        //creating film
//        Movie need = new Movie();
//        //creating film
//        films.put(name, need);
//    }
    
//    public void addMovie(String name)throws CatalogException{ //and etc
//        if(films.containsKey(name)) throw new CatalogException("Такой фильм уже есть в каталоге");
//        
//        if(!CatalogManager.movies.containsKey(name)){
//            CatalogManager
//        }
//        //creating film
//        Movie need = new Movie();
//        //creating film
//        films.put(name, need);
//    }
    
//    public void removeMovie(String name)throws CatalogException{
//        if(!films.containsKey(name)) throw new CatalogException();
//        //creating film
//        films.remove(name);
//    }
    
}
