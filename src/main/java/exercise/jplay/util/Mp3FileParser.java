package exercise.jplay.util;


import exercise.jplay.data.entity.AudioTrack;

import javax.sound.sampled.*;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Map;

public class Mp3FileParser implements AudioFileParser {


/*
    public static MP3File read(Path path) throws IOException, TagException,
            UnsupportedAudioFileException {
        MP3File file = new MP3File(path.toFile());
        if (file.hasID3v2Tag()) {
            ID3v2_2 tag = (ID3v2_2) file.getID3v2Tag();
            System.out.println(file.getMp3file());
            System.out.println(tag.getIdentifier());
            System.out.println(tag.getSongTitle());
            System.out.println(tag.getAlbumTitle());
            System.out.println(tag.getSongGenre());
            System.out.println(tag.getAuthorComposer());
            System.out.println(tag.getSongComment());
            System.out.println(tag.getSongLyric());
            System.out.println(tag.getSize());
        }




        AudioInputStream stream = AudioSystem.getAudioInputStream(path.toFile());
        AudioFormat format = stream.getFormat();
        long audioFileLength = path.toFile()
                                   .length();
        float frameSize = format.getFrameSize();
        float frameRate = format.getFrameRate();
        float sampleSize = format.getSampleSizeInBits();
        float sampleRate = format.getSampleRate();
        int channels = format.getChannels();
        System.out.println(format);
        System.out.println("___");
        System.out.println("Length: " + audioFileLength);
        System.out.println("Frame Size: " + frameSize);
        System.out.println("Frame Rate: " + frameRate);
        System.out.println("Sample Size: " + sampleSize);
        System.out.println("Sample Rate: " + sampleRate);
        System.out.println("Channels: " + channels);
        float durationInSeconds = (audioFileLength / (sampleRate * channels * sampleSize/8));
        System.out.println(durationInSeconds);
        return file;
    }
*/

    /*
     *   Audio File Format Properties
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
     *
     * @param path
     * @return
     * @throws UnsupportedAudioFileException
     * @throws IOException
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
        String filePath = path.toString();
        AudioTrack track = new AudioTrack();
        track.setFilePath(filePath);
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
