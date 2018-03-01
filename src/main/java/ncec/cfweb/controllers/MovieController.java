package ncec.cfweb.controllers;

import ncec.cfweb.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author DantalioNxxi
 */
@Controller
@RequestMapping("/movie")
public class MovieController {
    
    @Autowired
    MovieService movieService;
    
    
    
    
//    @RequestMapping("/movie/{movieId}")
////если в данном контроллере будет метод для обработки добавления фильма,
////то опустить movieId до уровня метода!
    
    
    
}
