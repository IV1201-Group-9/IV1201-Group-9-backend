package com.iv1201.recapp.Models.AuthDTO;

import jakarta.validation.constraints.*;

/**
 * Data transfer object to handle incoming HTTP requests for registration.
 */
public class RegisterRequestDTO {
    @Pattern(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\." +
            "[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"
            , message = "Please, check you email")
    private String email;

    @NotEmpty(message = "Password is missing")
    private String password;

    /**
     * Empty constructor
     */
    public RegisterRequestDTO() {
    }

    /**
     * Parameter constructor
     * @param email in request for signing up.
     * @param password in request for signing up.
     */
    public RegisterRequestDTO(String email,
                              String password) {
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
        return "RegisterRequest{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
