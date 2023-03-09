package com.iv1201.recapp.Models.ApplicantDTOs;

import jakarta.validation.constraints.Pattern;

/**
 *  Incoming DTO for Dates in <code>ApplicationDTO</code>
 */
public class DatesDTO {
    @Pattern(regexp = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))")
    String startDate;
    @Pattern(regexp = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))")
    String endDate;

    public DatesDTO() {
    }

    public DatesDTO(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        return "DatesDTO{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
