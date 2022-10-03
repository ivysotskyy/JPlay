package exercise.jplay;

import exercise.jplay.data.entity.AudioTrack;
import exercise.jplay.data.repository.AudioTrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class WebController {

    AudioTrackRepository repository;

    @Autowired
    public WebController(AudioTrackRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String index(Model model) {

        List<AudioTrack> tracks = repository.findAll();

        model.addAttribute("tracks", tracks);

        return "index";
    }
    // get all songs
    // select song
    // update selected song

}
