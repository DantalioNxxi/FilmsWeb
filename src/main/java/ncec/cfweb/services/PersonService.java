package ncec.cfweb.services;

import java.util.List;
import ncec.cfweb.Person;
import ncec.cfweb.User;

/**
 *
 * @author DantalioNxxi
 */
public interface PersonService {
    
    Person addPerson(Person person);
    
    void deleteById(Long id);
    
    Person getById(Long id);
    
    List<Person> getByFirstAndLastName(String firstname, String lastname);
    
    Person editPerson(Person person);//or ?..
    
    List<Person> getAll();
    
}
