package ncec.cfweb.services;

import java.util.List;
import ncec.cfweb.Movie;
import ncec.cfweb.User;

/**
 *
 * @author DantalioNxxi
 */
public interface UserService {
    
    void delete(String login);
    
    User getByLogin(String login);
    
    User getById(Long id);
    
    List<User> getByFirstAndLastName(String firstname, String lastname);
    
    User editUser(User user);//or ?..
    
    List<User> getAll();
    
}
