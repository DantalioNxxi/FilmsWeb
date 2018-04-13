package ncec.cfweb.repositories;

import java.util.List;
import ncec.cfweb.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author DantalioNxxi
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long>{
    
    User getById(Long id);
    
    void deleteByLogin(String login);

    void deleteById(Long id);
    
    User findByLogin(String login);
    
    List<User> findByFirstnameAndLastname(String firstname, String lastname);
    
}