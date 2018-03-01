package ncec.cfweb;

import java.util.Random;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.springframework.data.annotation.Id;

/**
 *
 * @author DantalioNxxi
 */
@Entity
@Table(name = "user_table")
public class User {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
//    private int id;
    
    @Getter
    @Setter
    private String firstname;
    
    @Getter
    @Setter
    private String lastname;
    
    //составной ключ
    private String login;
    
    //Is Need???
    @OneToMany(mappedBy = "creator", fetch = FetchType.EAGER)
    private Set<Catalog> catalogs;
    
    //Is Need???
    @OneToMany(mappedBy = "creator", fetch = FetchType.LAZY)
    private Set<Movie> movies;
    
    //wroten constructors

    protected User() {
    }
    

    public User(String firstname, String lasttname, String login) {
        this.firstname = firstname;
        this.lastname = lasttname;
        this.login = login;
        
//        this.id = computeId();
//        ides.add(id);
    }
    
    public Long getId() {
        return id;
    }

//    public String getFirstname() {
//        return firstname;
//    }
//
//    public String getLastname() {
//        return lastname;
//    }

    public String getLogin() {
        return login;
    }

//    public void addMovie(Movie movie){
//        //there is checking need?
//        movies.add(movie);
//    }
    
//    private static int computeId(){
//        int newid = 1;
//        while(ides.contains(newid)){
//            newid = 1 + new Random().nextInt(101);
//        }
//        return newid;
//    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", login=" + login + '}';
    }
    
    
}
