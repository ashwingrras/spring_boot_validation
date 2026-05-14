package org.example.spring_boot_validation.custom_validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DepartmentSalaryValidator.class)
public @interface ValidDepartmentSalary {

    String message() default
            "Managers must have salary >= 100000";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
