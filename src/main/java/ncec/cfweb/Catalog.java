package ncec.cfweb;

import java.util.HashMap;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Id;

/**
 *
 * @author DantalioNxxi
 */
@Entity
@Table(name = "catalog_table")
public class Catalog {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id", length = 8, updatable = false)
    private Long catalogId;
    
    private String name;
    
    //in generally, is it need? then optional i TRUE??????
    @ManyToOne(fetch = FetchType.EAGER, optional = true, cascade = CascadeType.ALL)
    private User creator;
    
    @OneToMany(fetch = FetchType.EAGER, targetEntity = Movie.class)
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

    public Long getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Long catalogId) {
        this.catalogId = catalogId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.catalogId);
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + Objects.hashCode(this.creator);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Catalog other = (Catalog) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.creator, other.creator)) {
            return false;
        }
        return true;
    }
    
    
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