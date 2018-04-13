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
    public void deleteById(Long id) {
        
    }
    
    @Override
    public Person addPerson(Person person) {// tm Person on void
        return personRepository.save(person);// tm not return
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

    //исправить на update!
    @Override
    public Person editPerson(String firstname, String lastname, int age) { // temporary
        List<Person> persons = personRepository.findByFirstnameAndLastname(firstname, lastname);
        if (persons.isEmpty()) {
            throw new IllegalArgumentException("Person not found + "+firstname +" "+lastname);
        } //it is need to old values!??!!!??!
        if (persons.size() > 1) {
            throw new IllegalArgumentException("Several persons exist with specified name");
        }

        Person person = persons.get(0);
        person.setFirstname(firstname);
        person.setLastname(lastname);
        person.setAge(age);
        
        return personRepository.save(person);
    }


    @Override
    public List<Person> getAll() {
        return (List<Person>)personRepository.findAll();
    }
}
