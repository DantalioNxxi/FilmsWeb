package ncec.cfweb.repositories;

import java.util.List;
import ncec.cfweb.User;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author DantalioNxxi
 */
public interface UserRepository extends CrudRepository<User, Long>{
    
    List<User> findByLogin(String login);
    
    List<User> deleteByLogin(String login);
    
}
