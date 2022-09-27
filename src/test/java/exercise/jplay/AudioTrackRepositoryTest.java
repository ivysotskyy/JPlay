package exercise.jplay;

import exercise.jplay.data.entity.AudioTrack;
import exercise.jplay.data.repository.AudioTrackRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
import java.util.List;

@SpringBootTest(classes = )
public class AudioTrackRepositoryTest {

    @Autowired
    AudioTrackRepository repository;

    @Test
    public void savesAudioTrackEntity() {

        Date date = new Date();
        String testTitle = "Test Title 1";
        AudioTrack track = new AudioTrack();
        track.setTitle(testTitle);
        track.setAuthor("Test Author");
        track.setDurationSeconds(1720);
        track.setFilePath("~/test/directory/example.mp3");
        track.setComment("comment");
        track.setDate(date);
        repository.save(track);

        List<AudioTrack> tracks = repository.findByTitle("Test Title 1");

        assertTrue(tracks.size() == 1);
        assertEquals(track, tracks.get(0));

    }
}
