package com.iv1201.recapp.Models.ApplicantDTOs;

import jakarta.validation.constraints.Pattern;

import java.util.Date;

public class DatesDTO {
    @Pattern(regexp = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))")
    Date from_date;
    @Pattern(regexp = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))")
    Date to_date;

    public DatesDTO() {
    }

    public DatesDTO(Date from_date, Date to_date) {
        this.from_date = from_date;
        this.to_date = to_date;
    }

    public Date getFrom_date() {
        return from_date;
    }

    public Date getTo_date() {
        return to_date;
    }

    @Override
    public String toString() {
        return "DatesDTO{" +
                "from_date=" + from_date +
                ", to_date=" + to_date +
                '}';
    }
}
