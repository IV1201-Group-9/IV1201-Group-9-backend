package com.iv1201.recapp.Config.Validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Date;

/**
 * - implemented.
 */
public class DateValidator implements ConstraintValidator<ValidateDate, Date> {

    @Override
    public boolean isValid(Date dateStr, ConstraintValidatorContext constraintValidatorContext) {
        Date current = new Date();
        int compare = dateStr.compareTo(current);
        System.out.println(dateStr);
        System.out.println(current);

        if(compare <=0){
            return false;
        }
        return true;
    }
}
