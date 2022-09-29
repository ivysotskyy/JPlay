package exercise.jplay.util.parser;

import exercise.jplay.data.entity.AudioTrack;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.nio.file.Path;

public interface AudioFileParser {
    AudioTrack parseAudioTrack(Path path) throws
            UnsupportedAudioFileException,
            IOException;

}
