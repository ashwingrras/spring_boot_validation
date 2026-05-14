package org.example.spring_boot_validation.custom_validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.spring_boot_validation.dto.EmployeeRequestDTO;

public class DepartmentSalaryValidator
        implements ConstraintValidator<
                ValidDepartmentSalary,
                EmployeeRequestDTO> {

    @Override
    public boolean isValid(EmployeeRequestDTO dto,
                           ConstraintValidatorContext context) {

        if (dto.getDepartment() == null ||
                dto.getSalary() == null) {
            return true;
        }

        if ("MANAGER".equalsIgnoreCase(dto.getDepartment())
                && dto.getSalary() < 100000) {

            context.disableDefaultConstraintViolation();

            context.buildConstraintViolationWithTemplate(
                            "Manager salary must be at least 100000")
                    .addPropertyNode("salary")
                    .addConstraintViolation();

            return false;
        }

        return true;
    }
}
