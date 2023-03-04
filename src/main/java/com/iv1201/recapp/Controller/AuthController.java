package com.iv1201.recapp.Controller;

import com.iv1201.recapp.Config.Exceptions.EmailAllreadyExcistsException;
import com.iv1201.recapp.Config.Exceptions.EmailNotFoundException;
import com.iv1201.recapp.Models.AuthDTO.AuthRequestDTO;
import com.iv1201.recapp.Models.AuthDTO.AuthResponseDTO;
import com.iv1201.recapp.Models.AuthDTO.RegisterRequestDTO;
import com.iv1201.recapp.Service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



/**
 * Controller for Authentication of user.
 */
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    /**
     * End-point for signing up new users.
     * @param registerRequestDTO defines the data needed to signing up new user.
     * @return A <code>ResponseEntity</code> if OK with token and role for the newly created user.
     */
//    , produces = MediaType.APPLICATION_XML_VALUE
    @PostMapping(value  = "/signup")
    public ResponseEntity<AuthResponseDTO> signup(
            @RequestBody @Valid RegisterRequestDTO registerRequestDTO
    ) throws EmailAllreadyExcistsException {
        return ResponseEntity.ok(authService.register(registerRequestDTO));
    }

    /**
     * End-point for authenticating user that want to access the rest of
     * the program.
     * @param authRequestDTO defines the data needed to authenticate the user.
     * @return A <code>ResponseEntity</code> if OK with new token and role of user.
     */
//    , produces = MediaType.APPLICATION_XML_VALUE
    @PostMapping(value = "/authenticate")
    public ResponseEntity<AuthResponseDTO> authenticate(
            @RequestBody @Valid AuthRequestDTO authRequestDTO
    ) throws EmailNotFoundException {
        return ResponseEntity.ok(authService.authenticate(authRequestDTO));
    }
}
