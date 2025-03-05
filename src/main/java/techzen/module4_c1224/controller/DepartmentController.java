package techzen.module4_c1224.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techzen.module4_c1224.model.Department;
import techzen.module4_c1224.service.IDepartmentService;
import techzen.module4_c1224.service.dto.res.JsonResponse;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final IDepartmentService departmentService;

    @GetMapping
    public ResponseEntity<?> getDepartments() {
        return JsonResponse.ok(departmentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable Integer id) {
        return JsonResponse.ok(departmentService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Department department) {
        return JsonResponse.created(departmentService.save(department));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Department department) {
        return JsonResponse.ok(departmentService.update(id, department));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        departmentService.deleteById(id);
        return JsonResponse.noContent();
    }
}
