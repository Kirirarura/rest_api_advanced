package config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@EntityScan("com.epam.esm.entity")
@Profile("integration-test")
public class EmbeddedDatabaseConfig {

}
