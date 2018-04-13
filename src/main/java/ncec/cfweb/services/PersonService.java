package ncec.cfweb.services;

import java.util.List;
import ncec.cfweb.Person;

/**
 *
 * @author DantalioNxxi
 */
public interface PersonService {
    
    Person addPerson(Person person);
    
    Person getById(Long id);
    
    List<Person> getByFirstAndLastName(String firstname, String lastname);
    
    Person editPerson(String firstname, String lastname, int age);
    
    List<Person> getAll();
    
    void deleteById(Long id);//tm
    
    
}
