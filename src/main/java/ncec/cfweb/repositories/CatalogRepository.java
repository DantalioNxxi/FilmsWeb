package ncec.cfweb.repositories;

import ncec.cfweb.Catalog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author DantalioNxxi
 */
@Repository
public interface CatalogRepository extends CrudRepository<Catalog, Long>{
    
}

////temporary
//    public static Set<String> countries;
//    
//    //temporary
//    public static Set<Catalog> catalogs;
//    
//    //temporary
////    public static HashSet<Movie> movies;
//    public static HashMap<String, Movie> movies;
//    
//    //Will he is adding a new genres?
//
//    //temporary... or just made it by senlethone
//    private CatalogManager() {
//        
//    }
//    
//    //void or boolean (at least boolean can be useless into check by web)
//    public static void addCountry(String name){
//        if(countries.contains(name)) {}//anything...
//        else countries.add(name);
//    }
//    
//    //void or boolean (at least boolean can be useless into check by web)
//    public static void addCatalog(Catalog name){
//        if(catalogs.contains(name)) {}//anything...
//        else catalogs.add(name);
//    }
//    
//    //void or boolean (at least boolean can be useless into check by web)
//    public static void addMovie(Movie movie)throws CatalogException{
//        if(movies.containsKey(movie.getTitle())) {throw new CatalogException("Фильм с таким именем уже есть в менеджере");}//anything...
//        else if(movies.containsValue(movie)) {throw new CatalogException("Такой фильм уже есть в менеджере");}
//        else movies.put(movie.getTitle(), movie);
//    }