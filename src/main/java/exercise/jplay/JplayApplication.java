package exercise.jplay;

import exercise.jplay.util.ApplicationStartup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class JplayApplication {
    private static final Logger log = LoggerFactory.getLogger(JplayApplication.class);

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(JplayApplication.class, args);
        //context.getBean(ApplicationStartup.class);
    }


}
