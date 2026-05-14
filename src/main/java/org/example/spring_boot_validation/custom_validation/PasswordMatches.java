package org.example.spring_boot_validation.custom_validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*

    Validation groups allow selective execution of constraints
    depending on operation context like create, update, admin,
    or publish.

    Payload allows attaching custom metadata such as severity or
    categorization to validation constraints without affecting
    validation execution.



 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchesValidator.class)
public @interface PasswordMatches {

    String message() default "Passwords do not match";

    Class<?>[] groups() default {};
    // for create / update decision ( groups : Create.class )
    //groups = {Create.class, Admin.class}

    Class<? extends Payload>[] payload() default {};
}
