package org.example.spring_boot_validation.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.spring_boot_validation.dto.EmployeeRequestDTO;
import org.example.spring_boot_validation.entity.Employee;
import org.example.spring_boot_validation.service.EmployeeService;
import org.example.spring_boot_validation.service.ValidationService;
import org.example.spring_boot_validation.validation_group.Create;
import org.example.spring_boot_validation.validation_group.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employees")
////@RequiredArgsConstructor
public class EmployeeController {

    @Autowired
    private final EmployeeService service;

    private final ValidationService validationService;

    public EmployeeController(EmployeeService service, ValidationService validationService) {
        this.service = service;
        this.validationService = validationService;
    }


    @PostMapping
    public ResponseEntity<Employee> createEmployee(
            @Validated({Create.class})
            @RequestBody EmployeeRequestDTO dto) {

        return ResponseEntity.ok(
                service.createEmployee(dto)
        );
    }

    @PutMapping
    public void update(@Validated(Update.class)
            @RequestBody EmployeeRequestDTO dto) {
    }


    @PostMapping("/validate")
    public ResponseEntity<?> validateEmployee(
            @RequestBody EmployeeRequestDTO request) {

        List<Map<String, Object>> errors =
                validationService.validate(request);

        if (!errors.isEmpty()) {
            return ResponseEntity.badRequest().body(errors);
        }

        return ResponseEntity.ok("Validation Passed");
    }

}
