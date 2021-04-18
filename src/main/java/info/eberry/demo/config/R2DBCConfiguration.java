package info.eberry.demo.config;


import io.r2dbc.h2.H2ConnectionConfiguration;
import io.r2dbc.h2.H2ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableR2dbcRepositories
@PropertySource("${config.properties.location:classpath:config.properties}")
class R2DBCConfiguration extends AbstractR2dbcConfiguration {
    @Value("${h2.db.location:./data/h2}")
    private String h2DatabaseLocation;

    @Bean(name="h2ConnectionFactory")
    public H2ConnectionFactory connectionFactory() {
        return new H2ConnectionFactory(
                H2ConnectionConfiguration.builder()
//                        .url("r2dbc:h2:mem:default;DB_CLOSE_DELAY=-1;")
//                        .url("jdbc:h2:file:./data/h2")
                        .file(h2DatabaseLocation)
//                        .url(dbUrl)
                        .username("sa")
                        .build()
        );
    }
}
