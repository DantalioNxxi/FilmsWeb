package ncec.cfweb.repositories;

import ncec.cfweb.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author DantalioNxxi
 */
@Repository
public interface MovieRepository extends CrudRepository<Movie, Long>{
    
    Movie findByName(String name);//is rightly? there is not necessary a query-format?
    
    
    
}
