package org.example.spring_boot_validation.custom_validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.spring_boot_validation.dto.EmployeeRequestDTO;

public class PasswordMatchesValidator
        implements ConstraintValidator<
                PasswordMatches,
                EmployeeRequestDTO> {

    @Override
    public boolean isValid(EmployeeRequestDTO dto,
                           ConstraintValidatorContext context) {

        if (dto.getPassword() == null ||
                dto.getConfirmPassword() == null) {
            return false;
        }

        boolean valid =
                dto.getPassword()
                        .equals(dto.getConfirmPassword());

        if (!valid) {

            context.disableDefaultConstraintViolation();

            context.buildConstraintViolationWithTemplate(
                            "Confirm password must match password")
                    .addPropertyNode("confirmPassword")
                    .addConstraintViolation();
        }

        return valid;
    }
}
