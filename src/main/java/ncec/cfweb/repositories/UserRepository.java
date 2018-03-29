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

//public class UserManager {
//    
//    //possible static - is temporary
//    public static HashMap<String, User> users = new HashMap<>();
//    
//    //public create user
//    
//    //or void or boolean?
//    public static void addUser(User user) throws UserException{
//        if(users.containsValue(user)){
//            throw new UserException("Пользователь уже в списке");}
//        else if(users.containsKey(user.getLogin())){
//            throw new UserException("Пользователя с таким логином уже существует!");
//        } else {
//            users.put(user.getLogin(), user);
//        }
//    }
//    
//    //or void or boolean?
//    public static void removeUser(String login) throws UserException{
//        if(!users.containsKey(login)){
//            throw new UserException("Пользователя с таким логином уже существует!");
//        } else {
//            users.remove(login);
//        }
//    }
//    
//}