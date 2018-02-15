
package ncec.cfweb;

import java.util.EnumSet;
import java.util.HashMap;

/**
 *
 * @author DantalioNxxi
 */
public class Person {
    
    Gender gender;
    String firstname;
    String lasttname;
    int age;
    
    EnumSet<Role.RoleMovie> career;
    HashMap<String, EnumSet<Role.RoleMovie>> films;

    public Person(Gender gender, String firstname, String lasttname, int age) {
        this.gender = gender;
        this.firstname = firstname;
        this.lasttname = lasttname;
        this.age = age;
    }
    
    public void setMovie(String name, EnumSet<Role.RoleMovie> roles){
        films.put(name, roles);
        career.addAll(roles);
    }
    
    public void addRole(Role.RoleMovie role){
        career.add(role);
    }
    
    
}
