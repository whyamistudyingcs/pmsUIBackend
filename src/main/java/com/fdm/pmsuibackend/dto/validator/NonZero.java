package com.fdm.pmsuibackend.dto.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NonZeroValidator.class)
public @interface NonZero {
    String message() default "Quantity cannot be zero";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
