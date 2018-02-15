package ncec.cfweb;

/**
 *
 * @author DantalioNxxi
 */
public class FabricMovies {
    
    //is need?
    public Movie getMovie(String name){
        Movie need;
        if(!CatalogManager.movies.containsKey(name)){
            //offer to User for the creating new Film
            need = new Movie();
        } else need = CatalogManager.movies.get(name);
        return need;
    }
    
    
}
