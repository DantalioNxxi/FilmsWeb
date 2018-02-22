package ncec.cfweb;

import java.util.Set;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author DantalioNxxi
 */
public class Personage {
    
    private Long id;
    private String fullname;
    
    @ManyToMany(mappedBy = "creator", fetch = FetchType.EAGER)//columns
    private Set<Movie> movies;
    
    @ManyToMany
    private Set<Movie> actors;
    
    public Personage() {
    }

    public Personage(String fullname) {
        this.fullname = fullname;
    }
    
}
