package ncec.cfweb;

import java.io.File;

/**
 *
 * @author DantalioNxxi
 */
public class Profile {
    
    User owner;
    
    String login;
    
    String password;
    
    //as variant
    File profile;

    public Profile(User owner, String login, String password) {
        this.owner = owner;
        this.login = login;
        this.password = password;
        
        profile = new File("where is?");
    }
    
    
}
