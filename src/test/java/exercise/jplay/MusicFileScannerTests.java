package exercise.jplay;

import static org.junit.jupiter.api.Assertions.*;

import exercise.jplay.util.MusicFileScanner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.List;

public class MusicFileScannerTests {
    private String samples = "E:\\Projects\\JPlay\\src\\test\\resources\\samples";

    @Test
    @DisplayName("Schould find two mp3 files in test/resources/samples directory")
    void findsFiles() {
        Path path = Path.of(samples);
        List<Path> files = null;
        try {
            files = MusicFileScanner.findFiles(List.of(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(2, files.size());
    }
}
