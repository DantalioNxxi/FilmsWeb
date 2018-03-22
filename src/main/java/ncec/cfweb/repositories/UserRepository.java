package ncec.cfweb.repositories;

import java.util.List;
import ncec.cfweb.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author DantalioNxxi
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long>{
    
    @Query("select u from User u where u.login = :login")
    User getByLogin(@Param("login") String login);
    
    User getById(Long id);
    
    //there is necessary the query-format?
    void deleteByLogin(String login);

    void deleteById(Long id);
    
    User findByLogin(String login);
    
    @Query("select u from User u where u.firstname = :fname and u.lastname = :lname")
    List<User> getByFirstAndLastName(@Param("fname") String firstname, @Param("lname") String lastname);
    
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