package ncec.cfweb.services.impl;

import java.util.List;
import ncec.cfweb.Person;
import ncec.cfweb.repositories.PersonRepository;
import ncec.cfweb.services.PersonService;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

/**
 *
 * @author DantalioNxxi
 */
@Configuration
@Service
public class PersonServiceImpl implements PersonService{
    
    PersonRepository personRepository;

    @Override
    public Person addPerson(Person person) {
        //...
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteById(Long id) {
        personRepository.delete(id);
    }
    
    //find or get???
    @Override
    public Person getById(Long id) {
        return personRepository.findById(id);
    }

    @Override
    public List<Person> getByFirstAndLastName(String firstname, String lastname) {
        return personRepository.findByFirstAndLastName(firstname, lastname);
    }

    @Override
    public Person editPerson(Person person) {
        //...
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //Is it rightly?????
    @Override
    public List<Person> getAll() {
        return (List<Person>)personRepository.findAll();
    }
    
}
