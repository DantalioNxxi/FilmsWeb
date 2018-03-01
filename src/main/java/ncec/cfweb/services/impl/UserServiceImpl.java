package ncec.cfweb.services.impl;

import java.util.List;
import ncec.cfweb.Movie;
import ncec.cfweb.User;
import ncec.cfweb.repositories.UserRepository;
import ncec.cfweb.services.UserService;

/**
 *
 * @author DantalioNxxi
 */
public class UserServiceImpl implements UserService{
    
    UserRepository userRepository;

    @Override
    public User addUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String login) {
        User delUser = userRepository.getByLogin(login);
        userRepository.delete(delUser);
    }

    @Override
    public User getByLogin(String login) {
        return userRepository.getByLogin(login);
    }

    @Override
    public User getByFirstAndLastName(String firstname, String lastname) {
        return userRepository.getByFirstAndLastName(firstname, lastname);
    }

    @Override
    public User editUser(User user) {
        //...
        return userRepository.save(user);//newUser
    }

    @Override
    public List<User> getAll() {
        return (List<User>)userRepository.findAll();//Does it cast?
    }
    
    
    
}
