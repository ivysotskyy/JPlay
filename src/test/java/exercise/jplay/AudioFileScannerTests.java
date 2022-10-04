package exercise.jplay;

import static org.junit.jupiter.api.Assertions.*;

import exercise.jplay.util.AudioFileScanner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class AudioFileScannerTests {
    private String samples = "E:\\Projects\\JPlay\\src\\test\\resources\\samples";

    @Test
    @DisplayName("Schould find two mp3 files in test/resources/samples directory")
    void findsFiles() {
        Path path = Path.of(samples);
        List<Path> files = null;
        try {
            files = AudioFileScanner.findFiles(List.of(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertTrue(files.size() > 0);
    }
}
