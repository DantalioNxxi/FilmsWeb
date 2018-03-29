package ncec.cfweb;

import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Id;

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
    
    private String firstname;
    private String lastname;
    
    private String login;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "creator")
    private Set<Catalog> catalogs;  //Is Need???
    
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

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.firstname);
        hash = 29 * hash + Objects.hashCode(this.lastname);
        hash = 29 * hash + Objects.hashCode(this.login);
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
        final User other = (User) obj;
        if (!Objects.equals(this.catalogs, other.catalogs)) {
            return false;
        }
        if (!Objects.equals(this.movies, other.movies)) {
            return false;
        }
        return true;
    }
    
    
}
