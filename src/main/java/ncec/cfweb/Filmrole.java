package ncec.cfweb;

import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

/**
 *
 * @author DantalioNxxi
 */
@Entity
public class Filmrole {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String fullname;
    
    @Transient
//    @ManyToMany(mappedBy = "creator", fetch = FetchType.EAGER)//columns
    private Set<Movie> movies;
    
    @Transient
//    @ManyToMany(fetch = FetchType.LAZY) //mappedBy = "personages"
//    @JoinTable(name = "PERSONAGE_ACTORS",
//            joinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "ACTOR_ID", referencedColumnName = "id")
//    )
    private Set<Movie> actors;
    
    public Filmrole() {
    }

    public Filmrole(String fullname) {
        this.fullname = fullname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.id);
        hash = 17 * hash + Objects.hashCode(this.fullname);
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
        final Filmrole other = (Filmrole) obj;
        if (!Objects.equals(this.fullname, other.fullname)) {
            return false;
        }
        if (!Objects.equals(this.movies, other.movies)) {
            return false;
        }
        return true;
    }
    
}
