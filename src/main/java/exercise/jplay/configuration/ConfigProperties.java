package exercise.jplay.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;

@Configuration
@ConfigurationProperties(prefix = "jplay.audio")
public class ConfigProperties {
    // Adding a custom configuration to application.properties
    // to configure initial directory to look for
    // audio files at the start of the application.
    private Path baseDir;

    public Path getBaseDir() {
        return baseDir;
    }

    public void setBaseDir(Path baseDir) {
        this.baseDir = baseDir;
    }

}
