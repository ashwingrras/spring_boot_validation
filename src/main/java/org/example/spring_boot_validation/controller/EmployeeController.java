package org.example.spring_boot_validation.controller;

import lombok.RequiredArgsConstructor;
import org.example.spring_boot_validation.dto.EmployeeRequestDTO;
import org.example.spring_boot_validation.entity.Employee;
import org.example.spring_boot_validation.service.EmployeeService;
import org.example.spring_boot_validation.validation_group.Create;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService service;

    @PostMapping
    public ResponseEntity<Employee> createEmployee(
            @Validated({Create.class})
            @RequestBody EmployeeRequestDTO dto) {

        return ResponseEntity.ok(
                service.createEmployee(dto)
        );
    }

    /*
        @PutMapping
        public void update(
            @Validated(UpdateGroup.class)
            @RequestBody EmployeeDTO dto) {
        }

     */
}
