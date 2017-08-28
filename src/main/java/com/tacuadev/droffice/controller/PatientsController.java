package com.tacuadev.droffice.controller;

import com.tacuadev.droffice.model.PatientModel;
import com.tacuadev.droffice.service.PatientService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fd on 23/08/17.
 */
@RestController
@RequestMapping("/patients")
public class PatientsController {
    private static final Log LOG = LogFactory.getLog(PatientsController.class);

    @Autowired
    @Qualifier("patientService")
    PatientService patientService;

    @GetMapping("/createPatient")
    public ModelAndView createPatient(){
        return new ModelAndView("/patients/create");
    }

    @PostMapping("/savePatient")
    public void savePatient(HttpServletRequest request, HttpServletResponse response){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");

        try{
            PatientModel patientModel = new PatientModel();
            patientModel.name = request.getParameter("name");
            patientModel.documentNumber = request.getParameter("document_number");
            patientModel.address = request.getParameter("address");
            if(request.getParameter("sex") != null && request.getParameter("sex").equals("M")){
                patientModel.sex = PatientModel.PATIENT_MALE_STRING;
                patientModel.sexInt = PatientModel.PATIENT_MALE;
            }else{
                patientModel.sex = PatientModel.PATIENT_FEMALE_STRING;
                patientModel.sexInt = PatientModel.PATIENT_FEMALE;
            }

            String openingDateString = request.getParameter("opening_date");

            try {
                Date openingDate = sdf.parse(openingDateString);
                LOG.info(request.getParameter("birthday"));
//                Date birthday = (Date)
//                        sdf.parse(request.getParameter("birthday"));
//                LOG.info("birthday"+birthday);
//                patientModel.openingDate = openingDate;
//                patientModel.birthday = birthday;
            }catch (Throwable th){
                LOG.info(th.getMessage());
            }



            patientService.savePatient(patientModel);
            //LOG.info("FECHA DATEPICKER: "+request.getParameter("opening_date"));
//            patientModel.openingDate = request.getParameter("opening_date");
        }catch (Throwable th){
            LOG.info(th.getMessage());
        }
    }

}
