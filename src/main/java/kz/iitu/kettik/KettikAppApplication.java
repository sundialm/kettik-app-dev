package kz.iitu.kettik;

import kz.iitu.kettik.configuration.StorageProperties;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class KettikAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(KettikAppApplication.class, args);
    }

    @Bean
    public ModelMapper configureModelMapper(){
        ModelMapper mapper = new ModelMapper();
// hhh
        mapper
                .getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);

        return mapper;
    }
}
