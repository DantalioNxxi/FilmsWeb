package ncec.cfweb.repositories;

import ncec.cfweb.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author DantalioNxxi
 */
@Repository
public interface GenreRepository extends CrudRepository<Genre, Integer>{
    
}
