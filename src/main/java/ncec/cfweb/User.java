package ncec.cfweb;

import java.util.Random;
import java.util.Set;

/**
 *
 * @author DantalioNxxi
 */
public class User {
    
    private static Set<Integer> ides;
    
    private int id;
    
    private String firstname;
    
    private String lasttname;
    
    private String login;
    
    private Set<Catalog> catalogs;
    
    private Set<Movie> movies;
    
    private Profile profile;
    
    //wroten constructors

    public User(String firstname, String lasttname, String login) {
        this.firstname = firstname;
        this.lasttname = lasttname;
        this.login = login;
        
        this.id = computeId();
        ides.add(id);
    }
    
    private static int computeId(){
        int newid = 1;
        while(ides.contains(newid)){
            newid = 1 + new Random().nextInt(101);
        }
        return newid;
    }

    public int getId() {
        return id;
    }
    
    public void addMovie(Movie movie){
        //there is checking need?
        movies.add(movie);
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLasttname() {
        return lasttname;
    }

    public String getLogin() {
        return login;
    }

    public Profile getProfile() {
        return profile;
    }
    
    
    
    
}
