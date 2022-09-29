package exercise.jplay.configuration;

import exercise.jplay.AudioTrackService;
import exercise.jplay.util.ApplicationStartup;
import exercise.jplay.util.parser.Mp3FileParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public Mp3FileParser mp3Parser() {
        return new Mp3FileParser();
    }

    @Bean
    public ApplicationStartup startup() {
        return new ApplicationStartup();
    }

}
