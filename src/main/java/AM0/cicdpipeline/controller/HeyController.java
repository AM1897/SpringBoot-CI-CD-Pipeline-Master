package AM0.cicdpipeline.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("")
public class HeyController {
    @GetMapping("")
    public String get() {
        return "Detta är ett test och ser att det fungerar!";
    }
}
