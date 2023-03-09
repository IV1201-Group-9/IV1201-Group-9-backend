package com.iv1201.recapp.Config.Exceptions.Util;

/**
 * Outgoing DTo exceptions sent to client.
 */
public class ExceptionsDTO {
    String errorForClient;

    public ExceptionsDTO() {
    }

    public ExceptionsDTO(String exceptionForClient) {
        this.errorForClient = exceptionForClient;
    }

    public String getErrorForClient() {
        return errorForClient;
    }

    public void setErrorForClient(String errorForClient) {
        this.errorForClient = errorForClient;
    }

    @Override
    public String toString() {
        return "ExceptionsDTO{" +
                "exceptionForClient='" + errorForClient + '\'' +
                '}';
    }
}
