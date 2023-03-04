package com.iv1201.recapp.Integration;

import com.iv1201.recapp.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface RoleRepo extends JpaRepository<Role, Long> {

    Role findRoleById(Long id );

}
