package ie.dit.dt354spring.configs;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"ie.dit.dt354spring.entities"})
@EnableJpaRepositories(basePackages = {"ie.dit.dt354spring.repositories"})
@EnableTransactionManagement
public class RepositoryConfig {
	
}
