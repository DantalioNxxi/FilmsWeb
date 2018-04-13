package ncec.cfweb.services.impl;

import java.util.List;
import ncec.cfweb.User;
import ncec.cfweb.repositories.UserRepository;
import ncec.cfweb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DantalioNxxi
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void delete(String login) {
//        User delUser = userRepository.getByLogin(login);
        userRepository.deleteByLogin(login);
    }

    @Override
    public User getByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public User getById(Long id) {
        return userRepository.getById(id);
    }
    
    @Override
    public List<User> getByFirstAndLastName(String firstname, String lastname) {
        return userRepository.findByFirstnameAndLastname(firstname, lastname);
    }

    //исправить на update!
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
