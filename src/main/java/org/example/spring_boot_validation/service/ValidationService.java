package org.example.spring_boot_validation.service;
import jakarta.validation.*;
import org.example.spring_boot_validation.dto.EmployeeRequestDTO;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ValidationService {

    private final Validator validator =
            Validation.buildDefaultValidatorFactory().getValidator();

    public List<Map<String, Object>> validate(EmployeeRequestDTO request) {

        System.out.println(" at ValidationService, validate");

        Set<ConstraintViolation<EmployeeRequestDTO>> violations =
                validator.validate(request);

        List<Map<String, Object>> errors = new ArrayList<>();

        //enhanced for loop / for each
        for (ConstraintViolation<EmployeeRequestDTO> violation : violations) {

            Map<String, Object> map = new HashMap<>();

            map.put("field", violation.getPropertyPath().toString());
            map.put("message", violation.getMessage());

            List<String> severities = new ArrayList<>();

            for (Class<? extends Payload> payload :
                    violation.getConstraintDescriptor().getPayload()) {

                severities.add(payload.getSimpleName());
            }

            map.put("severity", severities);

            errors.add(map);
        }

        return errors;
    }
}
