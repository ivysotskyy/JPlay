package exercise.jplay;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String hello(
            Model model,
            @RequestParam(value = "name", required = false, defaultValue = "World") String name
                       ) {
        model.addAttribute("name", name);
        return "hello";
    }

    @RequestMapping(path="/random", method = RequestMethod.GET)
    public String randomSong(Model model) {
        model.addAttribute("src", "/sound/file_example_MP3_2MG.mp3");
        return "random";
    }
}
