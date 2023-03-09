package com.iv1201.recapp.Config.Validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * Annotation for validation of expertise in <code>AreaOfExpertiseDTO</code>.
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ExpertiseValidator.class)
public @interface ValidateExpertise {
    public String message() default "Area of expertise does not exist";

    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {};
}