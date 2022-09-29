package exercise.jplay;

import exercise.jplay.data.entity.AudioTrack;
import exercise.jplay.data.repository.AudioTrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AudioTrackService {

    AudioTrackRepository repository;

    @Autowired
    public AudioTrackService(AudioTrackRepository repository) {
        this.repository = repository;
    }

    /**
     * ToDo
     *
     * @param track
     * @return
     */
    public int updateRepository(List<AudioTrack> track) {
        return 0;
    }

    /**
     * ToDo
     *
     * @param track
     * @return
     */
    public boolean updateAudioTrack(AudioTrack track) {
        return false;
    }

}
