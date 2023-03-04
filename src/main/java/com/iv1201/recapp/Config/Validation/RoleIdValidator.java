package com.iv1201.recapp.Config.Validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;


/**
 * Class for returning custom validation of id in <code>Role</code> class.
 */
public class RoleIdValidator implements ConstraintValidator<ValidateRoleId, Long> {
    /**
     * Check and return validity of id in <code>Role</code>.
     * @param longRoleId to validated.
     * @param constraintValidatorContext for validation.
     * @return
     */
    @Override
    public boolean isValid(Long longRoleId, ConstraintValidatorContext constraintValidatorContext) {
        List<Long> longRoleIds = Arrays.asList(1L,2L);
        return longRoleIds.contains(longRoleId);
    }
}
