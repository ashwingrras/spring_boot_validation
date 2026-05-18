package org.example.spring_boot_validation.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import org.example.spring_boot_validation.custom_validation.PasswordMatches;
import org.example.spring_boot_validation.custom_validation.StrongPassword;
import org.example.spring_boot_validation.custom_validation.UniqueEmail;
import org.example.spring_boot_validation.custom_validation.ValidDepartmentSalary;
import org.example.spring_boot_validation.validation_group.Create;
import org.example.spring_boot_validation.validation_group.Severity;
import org.example.spring_boot_validation.validation_group.Update;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

///@PasswordMatches
@ValidDepartmentSalary
public class EmployeeRequestDTO {

    /*
        @PasswordMatches(
            message = "...",
            groups = {...},
            payload = {...}
        )
     */

    @Null(groups = Create.class)
    @NotNull(message = "", groups = {Update.class})
    private Long id;

    @NotBlank(message = "Full name required")
    @Size(min = 3, max = 50, groups = Create.class)
    private String fullName;

    @Email
    //@UniqueEmail(groups = Create.class)
    private String email;

    //@StrongPassword()
    private String password;

    private String confirmPassword;

    @Min(value = 18)
    @Max(value = 60)
    private Integer age;

    @Positive
    private Double salary;

    @NotBlank
    private String department;

    //@PastOrPresent
    private LocalDate joiningDate;
}
