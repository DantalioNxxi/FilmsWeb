package ncec.cfweb.controllers;

import ncec.cfweb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author DantalioNxxi
 */
@Controller
@RequestMapping("/user/{userId}")
public class UserController {
    
    @Autowired
    UserService userService;
    
    
    
    
    
    
}
