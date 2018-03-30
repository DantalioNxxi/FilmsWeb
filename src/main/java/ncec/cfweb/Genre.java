package ncec.cfweb;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author DantalioNxxi
 */
@Entity
public class Genre {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String name;//must be is unique
    
    static{//how to...
//        FANTASTIC("Фантастика"),
//        FANTASY("Фэнтези"),
//        DRAMA("Драма"),
//        MELODRAMA("Мелодрама"),
//        THRILLER("Триллер"),
//        DETECTIVE("Детектив"),
//        HORROR("Ужасы"),
//        ADVENTURE("Приключение"),
//        COMEDY("Комедия");
    }

    public Genre(String name) {
        this.name = name;
    }

    protected Genre() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.name);
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
        final Genre other = (Genre) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
}
