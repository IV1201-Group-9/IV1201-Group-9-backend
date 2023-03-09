package com.iv1201.recapp.Controller;

import com.iv1201.recapp.Config.Exceptions.ApplicationCouldNotSubmitException;
import com.iv1201.recapp.Config.Exceptions.CouldNotFindCompetencesException;
import com.iv1201.recapp.Models.ApplicantDTOs.ApplicationDTO;
import com.iv1201.recapp.Models.Competence;
import com.iv1201.recapp.Service.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Applicant Controller for the applicants page in client.
 */
@RestController
@RequestMapping("/api/v1/applicants")
public class ApplicantController {

    @Autowired
    private ApplicationService applicationService;

    /**
     * End-point to get the competences from the database.
     * @return list of competences.
     * @throws CouldNotFindCompetencesException
     */
    @GetMapping("/competences")
    public ResponseEntity<List<Competence>> getAllApplicants()
            throws CouldNotFindCompetencesException {
        return ResponseEntity.ok(applicationService.getAllCompetence());
    }

    /**
     * End-point for submittion of application.
     * @param applicationDTO application information to be handled.
     * @return String of application being submitted successfully.
     * @throws ApplicationCouldNotSubmitException if service malfunction.
     */
    @PostMapping("/submit")
    public ResponseEntity<String> submitApplication(
            @RequestBody @Valid ApplicationDTO applicationDTO)
            throws ApplicationCouldNotSubmitException {

        applicationService.submitApplication(applicationDTO);
        return ResponseEntity.ok("Application was submitted successfully");
    }

//    todo deleteApplication if there is time
//    @DeleteMapping("/delete")
//    public ResponseEntity<String> deleteApplication(){
//        applicationService.deleteApplication();
//        return ResponseEntity.ok("Application was deleted successfully");
//    }
}
