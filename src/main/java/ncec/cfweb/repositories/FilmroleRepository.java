package ncec.cfweb.repositories;

import ncec.cfweb.Filmrole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author DantalioNxxi
 */
@Repository
public interface FilmroleRepository extends CrudRepository<Filmrole, Long>{
}
