package ncec.cfweb;

import java.util.HashMap;

/**
 *
 * @author DantalioNxxi
 */
public class UserManager {
    
    //possible static - is temporary
    public static HashMap<String, User> users = new HashMap<>();
    
    //public create user
    
    //or void or boolean?
    public static void addUser(User user) throws UserException{
        if(users.containsValue(user)){
            throw new UserException("Пользователь уже в списке");}
        else if(users.containsKey(user.getLogin())){
            throw new UserException("Пользователя с таким логином уже существует!");
        } else {
            users.put(user.getLogin(), user);
        }
    }
    
    //or void or boolean?
    public static void removeUser(String login) throws UserException{
        if(!users.containsKey(login)){
            throw new UserException("Пользователя с таким логином уже существует!");
        } else {
            users.remove(login);
        }
    }
    
}
