package com.iv1201.recapp.Config.Validation;

import com.iv1201.recapp.Models.ApplicantDTOs.AreaOfExpertiseDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

/**
 * Class for rcustom validation of expertises in  <code>AreaOfExpertiseDTO</code>
 */
public class ExpertiseValidator implements ConstraintValidator<ValidateExpertise, String> {

    @Override
    public boolean isValid(String expertise, ConstraintValidatorContext constraintValidatorContext) {
        List<String> expertiseTypes = Arrays.asList(
                "ticket sales",
                "lotteries",
                "roller coaster operation");
        return expertiseTypes.contains(expertise);
    }

}
