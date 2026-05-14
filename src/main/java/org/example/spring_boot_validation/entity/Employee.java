package org.example.spring_boot_validation.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true)
    private String email;

    private String password;

    private Integer age;

    private Double salary;

    private String department;

    private LocalDate joiningDate;
    /*
        @NotBlank(
        message = "Phone number missing",
        payload = Severity.Info.class
        )
        private String phone;

        @StrongPassword(
            message = "Weak password",
            payload = Severity.Warning.class
        )
        private String password;

        @FraudCheck(
            message = "Fraudulent activity detected",
            payload = Severity.Critical.class
        )
        private String transaction;
     */
}
