
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
//import org.springframework.boot.context.embedded.MultipartConfigFactory;


import java.io.IOException;
import javax.servlet.MultipartConfigElement;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author DantalioNxxi
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan //new
@EnableJpaRepositories(basePackages = "ncec.cfweb.repositories")
@Configuration
public class CFWebApplication {

    @Bean
    public Jackson2RepositoryPopulatorFactoryBean repositoryPopulator(ResourcePatternResolver resolver) throws IOException {
        Jackson2RepositoryPopulatorFactoryBean populator = new Jackson2RepositoryPopulatorFactoryBean();
        populator.setResources(resolver.getResources("classpath:/populator/*.json"));
        return populator;
    }
    
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("128KB");
        factory.setMaxRequestSize("128KB");
        return factory.createMultipartConfig();
    }
 
    public static void main(String[] args) {
        // System.out.println("11111111111111___222222222222");
        SpringApplication.run(CFWebApplication.class, args);
 
    }
}
