package ncec.cfweb;

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
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
}
