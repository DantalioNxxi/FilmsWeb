
package ncec.cfweb;

import ncec.cfweb.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;
import org.springframework.data.repository.init.Jackson2ResourceReader;
import org.springframework.data.repository.init.RepositoryPopulator;
import org.springframework.data.repository.init.ResourceReaderRepositoryPopulator;

import java.io.IOException;

/**
 *
 * @author DantalioNxxi
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "ncec.cfweb.repositories")
@Configuration
public class CFWebApplication {

    @Bean
    public Jackson2RepositoryPopulatorFactoryBean repositoryPopulator(ResourcePatternResolver resolver) throws IOException {
        Jackson2RepositoryPopulatorFactoryBean populator = new Jackson2RepositoryPopulatorFactoryBean();
        populator.setResources(resolver.getResources("classpath:/populator/*.json"));
        return populator;
    }
 
    public static void main(String[] args) {
        // System.out.println("11111111111111___222222222222");
        SpringApplication.run(CFWebApplication.class, args);
 
    }
}
