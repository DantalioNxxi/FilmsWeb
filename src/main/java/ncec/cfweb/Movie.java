
package ncec.cfweb;

import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Id;

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
    @ManyToOne(optional = true, cascade = CascadeType.ALL) // must be : optional = false
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
    
//    @OneToMany//or print in DB-field
    @ElementCollection
    private Set<String> countries;
    
    protected Movie() {
    }
    
    public Movie(String title, Date date, int duration) {
        this.title = title;
        this.date = date;
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setTitle(String title) {
        this.title = title;
    }
    

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
