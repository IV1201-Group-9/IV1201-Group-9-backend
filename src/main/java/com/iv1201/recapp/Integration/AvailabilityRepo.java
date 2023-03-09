package com.iv1201.recapp.Integration;

import com.iv1201.recapp.Models.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository for availability
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface AvailabilityRepo extends JpaRepository<Availability, Long> {
 // only uses save
}
