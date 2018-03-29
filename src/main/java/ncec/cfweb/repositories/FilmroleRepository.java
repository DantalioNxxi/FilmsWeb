/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncec.cfweb.repositories;

import ncec.cfweb.Filmrole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author DantalioNxxi
 */
@Repository
public interface FilmroleRepository extends CrudRepository<Filmrole, Long>{
    Filmrole getById(Long id);
}
