package com.iv1201.recapp.Service;

import com.iv1201.recapp.Config.Exceptions.StatusDTOException;
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

@Service
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
public class RecruiterService {
    @Autowired
    private UserRepo applicantRepo;
    public List<SingleUserApplicationDTO> getAllApplicants() throws StatusDTOException {
        List<User> applicants = null;
        List<SingleUserApplicationDTO> singleUserApplicationDTOs = new ArrayList<>();
        try {
            applicants = applicantRepo.findAllByApplicationStatusNotNullAndFirstnameNotNull();
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
                            application.getApplicationStatus()
                    ));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            });
            if(singleUserApplicationDTOs.size() == 0){
                throw new IllegalArgumentException();
            }
        }catch (Exception e ){
            throw new StatusDTOException("Could not get All Applicants");
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

    public void updateStatus(StatusDTO statusDTO) throws StatusDTOException {

        if (statusDTO == null) {
            throw new StatusDTOException("StatusDTO: " +
                    "Status value cannot be null");
        }

        try {
            User user = applicantRepo.findUserById(statusDTO.getId());
            user.setApplicationStatus(statusDTO.getStatus());
            applicantRepo.save(user);
        }catch (Exception e){
            throw new StatusDTOException("ApplicationDTO " +
                    "Status value could not be updated");
        }
    }
}
