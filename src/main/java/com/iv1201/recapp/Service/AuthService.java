package com.iv1201.recapp.Service;

import com.iv1201.recapp.Config.Exceptions.EmailAllreadyExcistsException;
import com.iv1201.recapp.Config.Exceptions.EmailNotFoundException;
import com.iv1201.recapp.Integration.RoleRepo;
import com.iv1201.recapp.Integration.UserRepo;
import com.iv1201.recapp.Models.Role;
import com.iv1201.recapp.Models.User;
import com.iv1201.recapp.Models.AuthDTO.AuthRequestDTO;
import com.iv1201.recapp.Models.AuthDTO.AuthResponseDTO;
import com.iv1201.recapp.Models.AuthDTO.RegisterRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Provides authentication service for end-point use in
 * <code>AuthController<code/>.
 * When called from Controller layer methods in this class starts transaction.
 * Transactions are completed when method returns no matter if the transaction
 * was fully committed or had to be rollback.
 */
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;

    /**
     * Authenticate user trying to sign in.
     * @param authRequestDTO defines the data needed to authenticate the user.
     * @return <code>authResponse<code/> object with token and role for client.
     */
    public AuthResponseDTO authenticate(AuthRequestDTO authRequestDTO) throws EmailNotFoundException {
        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        User user;
        Role role;

        try {
            UsernamePasswordAuthenticationToken upaToken =  new UsernamePasswordAuthenticationToken(
                    authRequestDTO.getEmail(),
                    authRequestDTO.getPassword());
            this.authenticationManager.authenticate(upaToken);
            System.out.println(SecurityContextHolder.getContext().getAuthentication());
            user = userRepo.findByEmail(authRequestDTO.getEmail());
            role = user.getUserRole();
        }catch (Exception e ){
            System.out.println(e);
           throw new EmailNotFoundException("Email could not be found");
        }
        authResponseDTO.setJwtToken(jwtService.createToken(user));
        authResponseDTO.setRole(role);
        return authResponseDTO;
    }

    /**
     * Register new users to the application
     * @param registerRequestDTO the defines the data needed to register new user.
     * @return <code>authResponse<code/> object with token and role for newly created
     * user.
     */
    public AuthResponseDTO register(RegisterRequestDTO registerRequestDTO) throws EmailAllreadyExcistsException {
        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        User userToAdd = new User();
        UserDetails existingUser;
        User addedUser;
        userToAdd.setEmail(registerRequestDTO.getEmail());
        userToAdd.setUsername(registerRequestDTO.getEmail());
        userToAdd.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        userToAdd.setUserRole(roleRepo.findRoleById(2L));

        try {
             existingUser = userService.loadUserByUsername(userToAdd.getUsername());
            if(userToAdd.getUsername().contains(existingUser.getUsername())){
                throw new EmailAllreadyExcistsException("User with this email has already been signed up");
            }
        }catch (EmailNotFoundException e){
            addedUser = userRepo.save(userToAdd);
            authResponseDTO.setJwtToken(jwtService.createToken(addedUser));
            authResponseDTO.setRole(userToAdd.getUserRole());
            return authResponseDTO;
        }
        return authResponseDTO;
    }





}