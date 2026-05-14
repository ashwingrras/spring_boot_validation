package org.example.spring_boot_validation.custom_validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.example.spring_boot_validation.repository.EmployeeRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniqueEmailValidator
        implements ConstraintValidator<UniqueEmail, String> {

    private final EmployeeRepository repository;

    @Override
    public boolean isValid(String email,
                           ConstraintValidatorContext context) {

        if (email == null) {
            return false;
        }

        return !repository.existsByEmail(email);
    }
}
