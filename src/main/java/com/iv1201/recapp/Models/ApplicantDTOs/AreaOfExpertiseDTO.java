package com.iv1201.recapp.Models.ApplicantDTOs;

import com.iv1201.recapp.Config.Validation.ValidateExpertise;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

/**
 *  Incoming DTO for area of expertise in <code>ApplicationDTO</code>.
 */
public class AreaOfExpertiseDTO {

    @Min(1)
    @Max(3)
    Long areaOfExpertiseID;
    @ValidateExpertise
    String expertise;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=2, fraction=2)
    BigDecimal yearsOfExpertise;

    public AreaOfExpertiseDTO() {
    }

    public AreaOfExpertiseDTO(Long areaOfExpertiseID, String expertise, BigDecimal yearsOfExpertise) {
        this.areaOfExpertiseID= areaOfExpertiseID;
        this.expertise = expertise;
        this.yearsOfExpertise = yearsOfExpertise;
    }

    public Long getAreaOfExpertiseID() {
        return areaOfExpertiseID;
    }

    public String getExpertise() {
        return expertise;
    }

    public BigDecimal getYearsOfExpertise() {
        return yearsOfExpertise;
    }

    @Override
    public String toString() {
        return "AreaOfExpertiseDTO{" +
                "areaOfExpertiseID=" + areaOfExpertiseID +
                ", expertise='" + expertise + '\'' +
                ", yearsOfExpertise=" + yearsOfExpertise +
                '}';
    }
}
