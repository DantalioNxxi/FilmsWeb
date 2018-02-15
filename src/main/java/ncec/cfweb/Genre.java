package ncec.cfweb;

/**
 *
 * @author DantalioNxxi
 */
public class Genre {
    
    /**
     * Or set, because can be adds a new genre. But then it is will being at class CatalogManager
     */
    public enum GenreType{
        FANTASTIC("Фантастика"),
        FANTASY("Фэнтези"),
        DRAMA("Драма"),
        MELODRAMA("Мелодрама"),
        THRILLER("Триллер"),
        DETECTIVE("Детектив"),
        HORROR("Ужасы"),
        ADVENTURE("Приключение"),
        COMEDY("Комедия");
        
        private final String name;

        private GenreType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
        
    }
    
}
