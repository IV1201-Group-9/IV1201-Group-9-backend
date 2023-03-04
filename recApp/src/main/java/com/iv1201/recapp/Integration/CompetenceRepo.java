package com.iv1201.recapp.Integration;

import com.iv1201.recapp.Models.Competence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface CompetenceRepo extends JpaRepository <Competence, Long> {

    Competence findCompetenceById(Long id);

    @Query("SELECT c FROM Competence c ORDER BY c.id ASC")
    List<Competence> findAllCompetences();
}
