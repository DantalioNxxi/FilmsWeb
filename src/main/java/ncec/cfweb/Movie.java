
package ncec.cfweb;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author DantalioNxxi
 */
public class Movie {
    
    private String name;
    
    //in generally, is it need?
    private User creator;
    
    private EnumSet<Genre.GenreType> genres;
    
    private HashMap<String, Person> actors;
    
    private Person director;
    
    private int year;
    
    private Set<String> countries;
    
    private int duration;
    
    private String description;

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Movie(String name, int year, int duration) {
        this.name = name;
        this.year = year;
        this.duration = duration;
    }

    public Movie() {
    }

    public String getName() {
        return name;
    }
    
    
    
    
    
    
    
}
