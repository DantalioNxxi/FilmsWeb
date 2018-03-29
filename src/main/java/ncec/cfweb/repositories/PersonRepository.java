package ncec.cfweb.repositories;

import java.util.List;
import ncec.cfweb.Person;
import ncec.cfweb.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author DantalioNxxi
 */
@Repository
public interface PersonRepository extends CrudRepository<Person, Long>{

    List<Person> findByFirstnameAndLastname(String firstname, String lastname);

    Person findById(Long id);
}
