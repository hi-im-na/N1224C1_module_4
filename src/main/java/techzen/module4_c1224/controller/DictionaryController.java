package techzen.module4_c1224.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import techzen.module4_c1224.service.dto.res.JsonResponse;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/dictionary")
public class DictionaryController {
    private final Map<String, String> dictEngViet = new HashMap<>(Map.of(
            "hello", "xin chào",
            "goodbye", "tạm biệt",
            "love", "yêu thương"
    ));

    @GetMapping
    public ResponseEntity<?> getDictionary(@RequestParam(defaultValue = "") String word) {
        String wordToSearch = word.toLowerCase().trim();
        if (dictEngViet.containsKey(wordToSearch)) {
            return JsonResponse.ok(dictEngViet.get(wordToSearch));
        }
        return JsonResponse.notFound("Không tìm thấy từ này trong từ điển");
    }
}
