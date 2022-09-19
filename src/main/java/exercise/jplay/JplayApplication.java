package exercise.jplay;


import exercise.jplay.data.entity.Track;
import exercise.jplay.data.repository.TrackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class JplayApplication {
    private static final Logger log = LoggerFactory.getLogger(JplayApplication.class);
    public static void main(String[] args) {

        SpringApplication.run(JplayApplication.class, args);
    }

    @Bean
    String demo(TrackRepository repository) {

        log.info(repository.findById(1).getTitle());
        return repository.findById(1).getTitle();
    }
}
