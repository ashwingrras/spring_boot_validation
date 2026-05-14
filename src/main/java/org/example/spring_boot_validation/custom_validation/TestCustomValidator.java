package org.example.spring_boot_validation.custom_validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TestCustomValidator implements ConstraintValidator<TestCustomValidationAnnotations, Integer> {
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {

        if(value == null)
        {
            return false;
        }
        return value >= 18;
    }
}
