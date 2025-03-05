package techzen.module4_c1224.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import techzen.module4_c1224.service.dto.res.JsonResponse;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    @GetMapping
    public ResponseEntity<?> hello(@RequestParam(defaultValue = "") String name) {
        return JsonResponse.ok(String.format("Hello %s!!!", name));
    }
}
