package org.example.spring_boot_validation.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.spring_boot_validation.dto.EmployeeRequestDTO;
import org.example.spring_boot_validation.entity.Employee;
import org.example.spring_boot_validation.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
@Validated
public class EmployeeService {

    private final EmployeeRepository repository;

    public Employee createEmployee(
            @Valid EmployeeRequestDTO dto) {

        Employee employee = Employee.builder()
                .fullName(dto.getFullName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .age(dto.getAge())
                .salary(dto.getSalary())
                .department(dto.getDepartment())
                .joiningDate(dto.getJoiningDate())
                .build();

        return repository.save(employee);
    }
}