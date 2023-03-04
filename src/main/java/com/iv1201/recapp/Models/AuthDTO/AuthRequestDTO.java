package com.iv1201.recapp.Models.AuthDTO;

import jakarta.validation.constraints.*;

//todo should we delete and only keep on of authRequestDTO and RegisterRequestDTO?
/**
 * Data transfer object to handle incoming HTTP requests for authentication.
 */
public class AuthRequestDTO {
    @Pattern(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\." +
            "[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"
            , message = "Please, check you email")
    private String email;

    @NotEmpty(message = "password is missing")
    private String password;

    /**
     * Empty constructor
     */
    public AuthRequestDTO() {
    }

    /**
     * Parameter constructor
     * @param email in request for authentication.
     * @param password in request for authentication.
     */
    public AuthRequestDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AuthenticationRequest{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
