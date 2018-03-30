package ncec.cfweb.services.impl;

import ncec.cfweb.Person;
import ncec.cfweb.repositories.PersonRepository;
import ncec.cfweb.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author DantalioNxxi
 */
@Service
public class PersonServiceImpl implements PersonService{

    @Autowired
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
        throw new UnsupportedOperationException();
//        return personRepository.findById(id);
    }

    @Override
    public List<Person> getByFirstAndLastName(String firstname, String lastname) {
        return personRepository.findByFirstnameAndLastname(firstname, lastname);
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
