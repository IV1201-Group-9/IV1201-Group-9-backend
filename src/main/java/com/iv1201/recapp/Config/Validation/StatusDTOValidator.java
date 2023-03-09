package com.iv1201.recapp.Config.Validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

/**
 * Class for returning custom validation of status of <code>Application</code>
 */
public class StatusDTOValidator implements ConstraintValidator<ValidateStatusDTO, String> {

    /**
     * Checks and returns validity of status in <code>ApplicationDTO</code>.
     * @param applicationStatusType to validated.
     * @param constraintValidatorContext used for validation.
     * @return true or false.
     */
    @Override
    public boolean isValid(
            String applicationStatusType,
            ConstraintValidatorContext constraintValidatorContext) {
        List<String> applicationStatusTypes = Arrays.asList("Accepted", "Rejected", "Unhandled");
        return applicationStatusTypes.contains(applicationStatusType);
    }

}
