package exercise.jplay.util.parser;


import exercise.jplay.data.entity.AudioTrack;

import javax.sound.sampled.*;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Map;

public class Mp3FileParser implements AudioFileParser {

    /*
     *   Audio File Format Properties in ID3
     * +-------------+------------+-----------------------------------------------+
     * |Property key | Value type | Description                                   |
     * |-------------|------------|-----------------------------------------------|
     * | "duration"  | Long       | playback duration of the file in microseconds |
     * | "author"    | String     | name of the author of this file               |
     * | "title"     | String     | title of this file                            |
     * | "copyright" | String     | copyright message                             |
     * | "date"      | Date       | date of the recording or release              |
     * | "comment"   | String     | an arbitrary text                             |
     * +-------------+------------+-----------------------------------------------+
     */

    /**
     * Extracts metadata from a .mp3 file and returns new
     * {@link exercise.jplay.data.entity.AudioTrack} instance.
     *
     * @param path Path to a mp3 music file.
     * @return {@link exercise.jplay.data.entity.AudioTrack}
     * @throws UnsupportedAudioFileException
     * @throws IOException
     * @see exercise.jplay.data.entity.AudioTrack AudioTrack
     * @see exercise.jplay.util.AudioFileScanner AudioFileScanner
     */
    @Override
    public AudioTrack parseAudioTrack(Path path) throws
            UnsupportedAudioFileException, IOException {

        AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(path.toFile());
        Map<String, Object> properties = fileFormat.properties();

        long duration = (Long) properties.get("duration");
        String title = (String) properties.get("title");
        String author = (String) properties.get("author");
        String album = (String) properties.get("album");
        String filePath = path.toFile()
                              .getAbsolutePath();
        AudioTrack track = new AudioTrack();
        track.setFilePath(filePath);
        track.setFileName(path.getFileName()
                              .toString());
        track.setTitle(title);
        track.setAuthor(author);
        track.setAlbum(album);
        track.setGenres(new HashSet<>());
        track.setDurationSeconds((int) (duration / 1e+6));

        return track;
    }

    private long getDurationSeconds() {
        return 0L;
    }
}
