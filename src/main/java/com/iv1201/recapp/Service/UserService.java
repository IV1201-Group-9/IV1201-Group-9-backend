package com.iv1201.recapp.Service;

import com.iv1201.recapp.Config.Exceptions.EmailNotFoundException;
import com.iv1201.recapp.Integration.UserRepo;
import com.iv1201.recapp.Models.User;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

/**
 * Provides user service for end-point use in <code>JwtFilter</code>.
 * When called from Controller layer methods in this class starts transaction.
 * Transactions are completed when method returns no matter if the transaction
 * was fully committed or had to be rollback.
 */
@Service
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
public class UserService implements UserDetailsService {
    @Autowired
    UserRepo userRepo;

    /**
     * Load user details by username, from user repository.
     * @param username username of user to be loaded from database.
     * @return UserDetails of user.
     * @throws UsernameNotFoundException if username did not exist.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = null;
        Collection<SimpleGrantedAuthority> authority = new ArrayList<>();
        try{
            user = userRepo.findByUsername(username);
            SimpleGrantedAuthority simpleGrantedAuthority= new SimpleGrantedAuthority(
                    " " + user.get().getUserRole().getRoleName());
            authority.add(simpleGrantedAuthority);
        }catch (Exception e){
            throw new EmailNotFoundException("Email not found");
        }

        return new org.springframework.security.core.userdetails.User(user.get().getUsername(),user.get().getPassword(), authority);
    }

}