package org.example.spring_boot_validation.validation_group;

import jakarta.validation.Payload;

public class Severity {

    public interface Info extends Payload {}

    public interface Warning extends Payload {}

    public interface Critical extends Payload {}

    public interface Debugging extends Payload {}
}