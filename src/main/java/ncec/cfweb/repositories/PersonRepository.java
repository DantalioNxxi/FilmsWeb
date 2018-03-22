package ncec.cfweb.repositories;

import java.util.List;
import ncec.cfweb.Person;
import ncec.cfweb.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author DantalioNxxi
 */
@Repository
public interface PersonRepository extends CrudRepository<Person, Long>{

    @Query("select u from Person u where u.firstname = :fname and u.lastname = :lname")
    List<Person> findByFirstAndLastName(@Param("fname") String firstname, @Param("lname") String lastname);
    
    Person findById(Long id);
    
    
}
