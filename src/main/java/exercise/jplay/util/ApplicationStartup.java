package exercise.jplay.util;

import exercise.jplay.data.entity.AudioTrack;
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
import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
            throw new RuntimeException("Failed to scan files in " +
                                               path + " while initial scan.", e);
        }

        ApplicationContext context = event.getApplicationContext();
        AudioFileParser parser = context.getBean(Mp3FileParser.class);
        AudioTrackRepository repository = context.getBean(AudioTrackRepository.class);
        List<AudioTrack> tracks = new ArrayList<>();
        for (Path p : audioFiles) {
            try {
                tracks.add(parser.parseAudioTrack(p));

            } catch (UnsupportedAudioFileException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException("Failed to parse " +
                                                   p.toString() + " while initial scan.\n", e);
            }
        }
        addMoreSongs(tracks); //test data
        repository.saveAll(tracks);
    }

    // Delete Me
    private void addMoreSongs(List<AudioTrack> tracks) {
        for(int i = 1; i <= 100; i++) {
            AudioTrack t = new AudioTrack();
            t.setTitle("example track Nr. " + i);
            t.setReleaseDate(Date.from(Instant.now()));
            t.setAuthor("Some Author");
            t.setComment("\""+i+"\"");
            t.setAlbum("Example album-"+i);
            t.setDurationSeconds(111 + i);
            t.setFilePath("test\\examples\\example-" + i + ".mp3");
            t.setGenres(Set.of("pop", "rock", "example"));
            t.setFileName("example-" + i + ".mp3");
            tracks.add(t);
        }
    }

}
