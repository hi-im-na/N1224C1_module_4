package techzen.module4_c1224.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techzen.module4_c1224.model.Employee;
import techzen.module4_c1224.service.IEmployeeService;
import techzen.module4_c1224.service.dto.req.EmployeeSearchRequest;
import techzen.module4_c1224.service.dto.res.JsonResponse;

import java.util.UUID;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final IEmployeeService employeeService;

    @GetMapping
    public ResponseEntity<?> getEmployees(EmployeeSearchRequest attr) {
        return JsonResponse.ok(employeeService.findByAttributes(attr));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable UUID id) {
        return JsonResponse.ok(employeeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Employee employee) {
        return JsonResponse.created(employeeService.save(employee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody Employee employee) {
        return JsonResponse.ok(employeeService.update(id, employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        employeeService.deleteById(id);
        return JsonResponse.noContent();
    }
}
