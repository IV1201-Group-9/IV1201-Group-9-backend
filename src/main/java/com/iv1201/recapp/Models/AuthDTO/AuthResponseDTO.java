package com.iv1201.recapp.Models.AuthDTO;

import com.iv1201.recapp.Models.Role;

public class AuthResponseDTO {
    private String jwtToken;
    private Role role;

    public AuthResponseDTO() {
    }

    public AuthResponseDTO(String jwtToken, Role role) {
        this.jwtToken = jwtToken;
        this.role = role;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "AuthResponse{" +
                "jwtToken='" + jwtToken + '\'' +
                ", role=" + role +
                '}';
    }
}
