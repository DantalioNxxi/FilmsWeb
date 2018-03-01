
package ncec.cfweb;

import ncec.cfweb.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *
 * @author DantalioNxxi
 */
@SpringBootApplication
//@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "ncec.cfweb.repositories")
public class CFWebApplication {
 
    public static void main(String[] args) {
        System.out.println("11111111111111___222222222222");
        SpringApplication.run(CFWebApplication.class, args);
 
    }
 
}
