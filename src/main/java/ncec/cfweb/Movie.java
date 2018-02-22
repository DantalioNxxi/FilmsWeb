
package ncec.cfweb;

import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.springframework.data.annotation.Id;

/**
 *
 * @author DantalioNxxi
 */
@Entity
public class Movie {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String title;
    private Date date;
    private int duration;
    
    private String description;//????how to set it????
    
    //in generally, is it need?
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private User creator;
    
    @OneToMany(targetEntity = Genre.class)
    private Set<Genre> genres;
    
//    private HashMap<String, Person> actors;
    @ManyToMany //??????????
    private Set<Person> actors;
    
    @ManyToMany//at future
    private Set<Person> personages;
    
    @ManyToOne(optional = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Person director;
    
    @OneToMany//or print in DB-field
    private Set<String> countries;
    
    protected Movie() {
    }
    
    public Movie(String title, Date date, int duration) {
        this.title = title;
        this.date = date;
        this.duration = duration;
    }
    
    
    public void setCreator(User creator) {
        this.creator = creator;
    }

    public User getCreator() {
        return creator;
    }
    
    public String getTitle() {
        return title;
    }
    
}
