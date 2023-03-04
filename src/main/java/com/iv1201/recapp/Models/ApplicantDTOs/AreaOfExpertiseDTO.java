package com.iv1201.recapp.Models.ApplicantDTOs;

public class AreaOfExpertiseDTO {
    Long areaOfExpertiseID;
    float yearsOfExperience;

    public AreaOfExpertiseDTO() {
    }

    public AreaOfExpertiseDTO(long areaOfExpertiseID, float yearsOfExperience) {
        this.areaOfExpertiseID = areaOfExpertiseID;
        this.yearsOfExperience = yearsOfExperience;
    }

    public long getAreaOfExpertiseID() {
        return areaOfExpertiseID;
    }

    public float getYearsOfExperience() {
        return yearsOfExperience;
    }

    @Override
    public String toString() {
        return "AreaOfExpertiseDTO{" +
                "areaOfExpertiseID=" + areaOfExpertiseID +
                ", yearsOfExperience='" + yearsOfExperience + '\'' +
                '}';
    }
}
