package com.iv1201.recapp.Service;

import com.iv1201.recapp.Config.Exceptions.RecruiterException;
import com.iv1201.recapp.Integration.UserRepo;
import com.iv1201.recapp.Models.RecruiterDTOs.SingleUserApplicationDTO;
import com.iv1201.recapp.Models.RecruiterDTOs.StatusDTO;
import com.iv1201.recapp.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Long.parseLong;

/**
 * Provides recruiter service for end-point use in <code>RecruiterController</code>.
 * When called from Controller layer methods in this class starts transaction.
 * Transactions are completed when method returns no matter if the transaction
 * was fully committed or had to be rollback.
 */
@Service
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
public class RecruiterService {
    @Autowired
    private UserRepo applicantRepo;

    /**
     * Gets all applications from database for recruiter.
     * @return <code>singleUserApplicationDTOs</code> Complete list of all applications.
     * @throws RecruiterException
     */
    public List<SingleUserApplicationDTO> getAllApplicants() throws RecruiterException {
        List<User> applicants = null;
        List<SingleUserApplicationDTO> singleUserApplicationDTOs = new ArrayList<>();
        try {
            applicants = applicantRepo.findAllByApplicationStatusNotNullAndFirstnameNotNullOrderById();
            if(applicants.size() == 0){
                throw new IllegalArgumentException();
            }
            applicants.forEach(application ->
            {
                try {
                    singleUserApplicationDTOs.add(new SingleUserApplicationDTO(
                            application.getId(),
                            application.getFirstname(),
                            application.getSurname(),
                            getAgeFromPnr(application.getPnr()),
                            application.getStatus()
                    ));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            });
            if(singleUserApplicationDTOs.size() == 0){
                throw new IllegalArgumentException();
            }
        }catch (Exception e ){
            throw new RecruiterException("Could not get All Applicants " + e);
        }
        return singleUserApplicationDTOs;
    }

    private int getAgeFromPnr(String pnr) throws ParseException {

        String[] split = pnr.split("-");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            Date dob = formatter.parse(split[0]);

            LocalDate dateNow = LocalDate.now();
            return Period.between(dob.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate(), dateNow).getYears();
    }

    /**
     * Update status of application in database.
     * @param statusDTO with information of status and id of user.
     * @throws RecruiterException if status fails to update.
     */
    public void updateStatus(StatusDTO statusDTO) throws RecruiterException {
        try {
            User user = applicantRepo.findUserById(statusDTO.getId());
            user.setStatus(statusDTO.getStatus());
            applicantRepo.save(user);
        }catch (Exception e){
            throw new RecruiterException("Status value could not be updated");
        }
    }
}
