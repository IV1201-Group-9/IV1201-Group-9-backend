package com.iv1201.recapp.Models.ApplicantDTOs;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import java.util.List;


/**
 *  Incoming DTO for Application information. Contains <code>AreaOfExpertise</code>
 *  and <code>DateDTO</code> for use and validation.
 */
public class ApplicationDTO {

    @NotEmpty(message = "Please, provide fist name")
    String firstName;

    @NotEmpty(message = "Please, provide surname")
    String lastName;
    
    @Pattern(regexp = "^(\\d{10}|\\d{12}|\\d{6}-\\d{4}|\\d{8}-\\d{4}|\\d{8} \\d{4}|\\d{6} \\d{4})"
            , message = "Please, Check your personal number"
    )
    String personalNumber;

    @Valid
    List<AreaOfExpertiseDTO> areaOfExpertiseDTOList;
    
    @Valid
    List<DatesDTO> datesDTOList;

    public ApplicationDTO() {
    }

    public ApplicationDTO(String firstName,
                          String lastName,
                          String personalNumber,
                          List<AreaOfExpertiseDTO> areaOfExpertiseDTOList,
                          List<DatesDTO> datesDTOList) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalNumber = personalNumber;
        this.areaOfExpertiseDTOList = areaOfExpertiseDTOList;
        this.datesDTOList = datesDTOList;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }


    public List<AreaOfExpertiseDTO> getAreaOfExpertiseDTOList() {
        return areaOfExpertiseDTOList;
    }

    public List<DatesDTO> getDatesDTOList() {
        return datesDTOList;
    }


    @Override
    public String toString() {
        return "ApplicationDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", personalNumber='" + personalNumber + '\'' +
                ", items=" + areaOfExpertiseDTOList +
                ", dateRanges=" + datesDTOList +
                '}';
    }
}
