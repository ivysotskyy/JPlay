package exercise.jplay.util;

import exercise.jplay.data.repository.AudioTrackRepository;
import exercise.jplay.util.parser.AudioFileParser;
import exercise.jplay.util.parser.Mp3FileParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * This bean is initialized in the {@link exercise.jplay.configuration.BeanConfiguration}
 * and executes task in different phases of the startup
 */
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Value("${jplay.audio.base-dir}")
    private String baseDir;


    /**
     * A callback method.
     * This method is called after application and all beans have been fully initialized and
     * is responsible for importing audio files from the base directory.
     * @param event
     */
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        Path path = Path.of(baseDir);
        List<Path> audioFiles;
        try {
            audioFiles = AudioFileScanner.findFiles(List.of(path));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Faild to scan files in " +
                                               path + "while initial scan.", e);
        }

        ApplicationContext context = event.getApplicationContext();
        AudioFileParser parser = context.getBean(Mp3FileParser.class);
        AudioTrackRepository repository = context.getBean(AudioTrackRepository.class);
        for (Path p : audioFiles) {
            try {
                repository.save(parser.parseAudioTrack(p));
            } catch (UnsupportedAudioFileException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException("Failed to parse " +
                                                   p.toString() + " while initial scan.\n", e);
            }
        }

    }

}
