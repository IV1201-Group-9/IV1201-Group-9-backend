package com.iv1201.recapp.Config.Validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * Could be used for validation of new roles - Not implemented.
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = RoleIdValidator.class)
public @interface ValidateRoleName {
    public String message() default "Role ID Value Invalid";

    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {};
}
