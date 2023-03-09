package com.iv1201.recapp.Models.RecruiterDTOs;

/**
 * Outgoing DTO for Updated Status.
 */
public class UpdatedStatusDTO {

    String updatedStatus;

    public UpdatedStatusDTO() {
    }

    public UpdatedStatusDTO(String updatedStatus) {
        this.updatedStatus = updatedStatus;
    }

    public String getUpdatedStatus() {
        return updatedStatus;
    }

    @Override
    public String toString() {
        return "UpdatedStatusDTO{" +
                "updatedStatus='" + updatedStatus + '\'' +
                '}';
    }
}
