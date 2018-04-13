package ncec.cfweb.repositories;

import java.util.List;
import ncec.cfweb.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author DantalioNxxi
 */
@Repository
public interface PersonRepository extends CrudRepository<Person, Person.PersonPk>{
    
    List<Person> findByFirstnameAndLastname(String firstname, String lastname);

//    Person findById(Person.PersonPk id);//tm
    
//    void deleteById(Person.PersonPk id);//tm
    
    void deleteByFirstnameAndLastname(String firstname, String lastname);//tm
    
}
