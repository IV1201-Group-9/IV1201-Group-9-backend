package com.iv1201.recapp.Integration;

import com.iv1201.recapp.Models.CompetenceProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository for CompetenceProfile.
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface CompetenceProfileRepo extends JpaRepository<CompetenceProfile, Long> {
// Only uses save.
}
