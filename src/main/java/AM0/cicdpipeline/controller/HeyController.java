package AM0.cicdpipeline.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("")
public class HeyController {
    @GetMapping("")
    public String get() {
        return "Nu ska vi se till om allt går igenom Github Actions och förbi all Pipeline vi har också på AWS!";
    }
}
