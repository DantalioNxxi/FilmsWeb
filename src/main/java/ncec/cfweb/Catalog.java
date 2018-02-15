package ncec.cfweb;

import java.util.HashMap;

/**
 *
 * @author DantalioNxxi
 */
public class Catalog {
    
    //need it?
    FabricMovies fm;
    
    //in generally, is it need?
    User creator;
    
    //the id generator, as potential GoF pattern user?
    int id;
    
    String name;
    
    //or simly set?
    HashMap<String, Movie> films;
    //HashSet<Movie> films;

    public Catalog(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Catalog(User creator, int id, String name) {
        this.creator = creator;
        this.id = id;
        this.name = name;
    }
    
    public Movie getMovie(String name){
        Movie need;
        if(!films.containsKey(name)){
            need = fm.getMovie(name);
        } else need = films.get(name);
        return need;
    }
    
    public void addMovie(Movie movie)throws CatalogException{ //and etc
        if(films.containsKey(movie.getName())) throw new CatalogException("Фильм с таким именем уже есть в каталоге");
        if(films.containsValue(movie)) throw new CatalogException("Такой фильм уже есть в каталоге");
        
        //if it is this new added film
        if(!CatalogManager.movies.containsValue(movie)){
            CatalogManager.addMovie(movie);
        }
        //creating film
        Movie need = new Movie();
        //creating film
        films.put(name, need);
    }
    
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
    
    public void removeMovie(String name)throws CatalogException{
        if(!films.containsKey(name)) throw new CatalogException();
        //creating film
        films.remove(name);
    }
    
}
