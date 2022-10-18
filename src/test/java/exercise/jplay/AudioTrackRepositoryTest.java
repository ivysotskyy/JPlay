package exercise.jplay;

import exercise.jplay.data.entity.AudioTrack;
import exercise.jplay.data.repository.AudioTrackRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
import java.util.List;

@DataJpaTest
@RunWith(SpringRunner.class)
public class AudioTrackRepositoryTest {

    @Autowired
    AudioTrackRepository repository;

    @Test
    public void savesAudioTrackEntity() throws InterruptedException {

        Date date = new Date();
        String testTitle = "Test Title 1";
        AudioTrack track = new AudioTrack();
        track.setTitle(testTitle);
        track.setAuthor("Test Author");
        track.setDurationSeconds(1720);
        track.setFilePath("~/test/directory/example.mp3");
        track.setComment("comment");
        track.setReleaseDate(date);
        repository.save(track);
        List<AudioTrack> tracks = repository.findByTitle("Test Title 1");
        assertEquals(1, tracks.size());
        assertEquals(track, tracks.get(0));

    }
}
