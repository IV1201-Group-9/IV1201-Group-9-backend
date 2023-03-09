package com.iv1201.recapp.Models.RecruiterDTOs;

import com.iv1201.recapp.Config.Validation.ValidateStatusDTO;
import jakarta.validation.constraints.Min;


/**
 *  Incoming DTO for update of status
 */
public class StatusDTO {

//    Because applicants start at id 11
    @Min(11)
    Long id;
    @ValidateStatusDTO
    String status;

    public StatusDTO() {
    }

    public StatusDTO(Long userID, String status) {
        this.id = userID;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "StatusDTO{" +
                "userID=" + id +
                ", status='" + status + '\'' +
                '}';
    }
}
