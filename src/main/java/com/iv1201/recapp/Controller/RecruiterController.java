package com.iv1201.recapp.Controller;

import com.iv1201.recapp.Config.Exceptions.RecruiterException;
import com.iv1201.recapp.Models.RecruiterDTOs.SingleUserApplicationDTO;
import com.iv1201.recapp.Models.RecruiterDTOs.StatusDTO;
import com.iv1201.recapp.Models.RecruiterDTOs.UpdatedStatusDTO;
import com.iv1201.recapp.Service.RecruiterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Recruiter Controller for the recruiter/admin page in client.
 */
@RestController
@RequestMapping("/api/v1/recruiters")
public class RecruiterController {
    @Autowired
    private RecruiterService recruiterService;

    /**
     * End-point to get all the applications sent in from the applicants.
     * @return list of all the applications.
     * @throws RecruiterException c
     */
    @GetMapping("/applicants")
    public ResponseEntity<List<SingleUserApplicationDTO>> getAllApplicants()
            throws RecruiterException {
        return ResponseEntity.ok(recruiterService.getAllApplicants());
    }

    /**
     * End-point for updating status on application.
     * @param statusDTO with id and status to be updated.
     * @return <code>UpdatedStatusDTO</code> to inform client of update.
     * @throws RecruiterException malfunction.
     */
    @PostMapping("/status")
    public ResponseEntity<UpdatedStatusDTO> setStatus(@RequestBody @Valid StatusDTO statusDTO)
            throws RecruiterException {
        recruiterService.updateStatus(statusDTO);
        return ResponseEntity.ok(new UpdatedStatusDTO("Status updated successfully"));

    }
}


