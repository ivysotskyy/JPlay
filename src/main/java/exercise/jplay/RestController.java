package exercise.jplay;

import exercise.jplay.data.entity.AudioTrack;
import exercise.jplay.data.repository.AudioTrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    AudioTrackRepository repository;

    @Autowired
    public RestController(AudioTrackRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/tracks")
    public List<AudioTrack> tracks() {
        return repository.findAll();
    }

    @PutMapping("/update")
    public AudioTrack update(@RequestBody AudioTrack track) {
        repository.save(track);
        return track;
    }

}
