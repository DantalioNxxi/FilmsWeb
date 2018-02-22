/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncec.cfweb.repositories;

import ncec.cfweb.Movie;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author DantalioNxxi
 */
public interface MovieRepository extends CrudRepository<Movie, Long>{
    
}
