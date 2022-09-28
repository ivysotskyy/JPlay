package exercise.jplay;

import exercise.jplay.data.repository.AudioTrackRepository;
import exercise.jplay.util.AudioFileParser;
import exercise.jplay.util.AudioFileScanner;
import exercise.jplay.util.Mp3FileParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Controller
public class HelloController {

    @Autowired
    AudioTrackRepository repository;
    AudioFileParser parser = new Mp3FileParser();
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String hello(
            Model model,
            @RequestParam(value = "name", required = false, defaultValue = "World") String name
                       ) {
        model.addAttribute("name", name);
        return "hello";
    }

    @RequestMapping(path="/random", method = RequestMethod.GET)
    public String randomSong(Model model) throws IOException,
            UnsupportedAudioFileException {
        //model.addAttribute("src", "/sound/file_example_MP3_2MG.mp3");
        for(Path p : AudioFileScanner.findFiles(List.of(Path.of("src/test/resources/samples")))) {
            repository.save(parser.parseAudioTrack(p));
        }

        repository.flush();
        return "random";
    }
}
