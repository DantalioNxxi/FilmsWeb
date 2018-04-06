
package ncec.cfweb;

import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author DantalioNxxi
 */
@Entity
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class Movie {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "MOVIE_ID")
    private Long id;

    @XmlElement
    private String title;

    @Temporal(TemporalType.DATE) //????????????
    private Date dateCreation;

    private int duration;

    @XmlElement
    private String description;//????how to set it????
    
    @ManyToOne(fetch = FetchType.EAGER, optional = true, cascade = CascadeType.ALL) // must be : optional = false
//    @JoinColumn(name = "CREATOR_ID")
    private User creator;
    
    @OneToMany(fetch = FetchType.EAGER, targetEntity = Genre.class)
    private Set<Genre> genres;
    
//    private HashMap<String, Person> persons;
//    @Transient
//    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})  //mappedBy = "movies"
//    @ManyToMany(mappedBy = "movies")
    
//    @JoinTable(name = "MOVIE_ACTORS",
//            joinColumns = @JoinColumn(name = "MOVIE_ID", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "PERSON_ID", referencedColumnName = "id")
//    )
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY) //Movie - is owner
    private Set<Person> persons;
    
    @Transient
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "MOVIE_PERSONAGES",
//            joinColumns = @JoinColumn(name = "MOVIE_ID"), //, referencedColumnName = "id"
//            inverseJoinColumns = @JoinColumn(name = "FILMROLE_ID", referencedColumnName = "id")
//    )
    private Set<Filmrole> personages;

    @ManyToOne(optional = true, fetch = FetchType.EAGER, cascade = {}) //каскадность пока убрал...
    private Person director;
    
//    @OneToMany//or print in DB-field
    @ElementCollection
    private Set<String> countries;
    
    public Movie() {
    }
    
    public Movie(String title, Date date, int duration) {
        this.title = title;
        this.dateCreation = date;
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
    

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
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

    public Person getDirector() {
        return director;
    }

    public void setDirector(Person director) {
        this.director = director;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.id);
        hash = 19 * hash + Objects.hashCode(this.title);
        hash = 19 * hash + Objects.hashCode(this.dateCreation);
        hash = 19 * hash + this.duration;
        hash = 19 * hash + Objects.hashCode(this.description);
        hash = 19 * hash + Objects.hashCode(this.creator);
        hash = 19 * hash + Objects.hashCode(this.genres);
        hash = 19 * hash + Objects.hashCode(this.persons);
        hash = 19 * hash + Objects.hashCode(this.personages);
        hash = 19 * hash + Objects.hashCode(this.director);
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
        final Movie other = (Movie) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.dateCreation, other.dateCreation)) {
            return false;
        }
        if (!Objects.equals(this.director, other.director)) {
            return false;
        }
        return true;
    }
    
    
    
}
