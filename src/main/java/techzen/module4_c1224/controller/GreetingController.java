package techzen.module4_c1224.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    @GetMapping
    public String hello(@RequestParam(defaultValue = "") String name) {
        return String.format("Hello %s!!!", name);
    }

}
