package techzen.module4_c1224.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import techzen.module4_c1224.service.dto.JsonResponse;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {
    @GetMapping
    public ResponseEntity<?> calculate(@RequestParam Double firstNNumber, @RequestParam Double secondNumber, @RequestParam String operator) {
        double result;
        switch (operator) {
            case "+" -> result = firstNNumber + secondNumber;
            case "-" -> result = firstNNumber - secondNumber;
            case "*" -> result = firstNNumber * secondNumber;
            case "/" -> {
                if (secondNumber == 0) {
                    return JsonResponse.badRequest("Can't divide by zero");
                }
                result = firstNNumber / secondNumber;
            }
            default -> {
                return JsonResponse.badRequest("Invalid operator");
            }
        }
        return JsonResponse.ok(result);
    }

}
