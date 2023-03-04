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

@RestController
@RequestMapping("/api/v1/applicants")
public class ApplicantController {

    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/competences")
    public ResponseEntity<List<Competence>> getAllApplicants()
            throws CouldNotFindCompetencesException {
        return ResponseEntity.ok(applicationService.getAllCompetence());
    }

    //might need HttpServletRequest request to get token
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
