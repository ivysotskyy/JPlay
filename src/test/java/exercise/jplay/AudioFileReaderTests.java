package exercise.jplay;

import static org.junit.jupiter.api.Assertions.*;


import exercise.jplay.data.entity.AudioTrack;
import exercise.jplay.util.parser.Mp3FileParser;
import org.farng.mp3.TagException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;

public class AudioFileReaderTests {
    static String path;

    @BeforeAll
    static void setUp() {
        path = "src\\test\\resources\\samples\\Death Grips - Get Got.mp3";
    }


    @Test
    void parsesTitleAndPath() throws IOException, TagException,
            UnsupportedAudioFileException, SQLException {

        Mp3FileParser parser = new Mp3FileParser();

        AudioTrack track = parser.parseAudioTrack(Path.of("src/test/resources/samples/Death Grips - Get Got.mp3"));

        assertEquals("Get Got", track.getTitle());
        assertEquals(path, track.getFilePath());

    }
}
