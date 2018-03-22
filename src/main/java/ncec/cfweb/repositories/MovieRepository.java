package ncec.cfweb.repositories;

import java.util.List;
import ncec.cfweb.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author DantalioNxxi
 */
@Repository
public interface MovieRepository extends CrudRepository<Movie, Long>{
    
    List<Movie> findByTitle(String name);//is rightly? there is not necessary a query-format?
    
    Movie findById(Long id);

    void deleteByTitle(String title);

    @Override
    List<Movie> findAll();
}
