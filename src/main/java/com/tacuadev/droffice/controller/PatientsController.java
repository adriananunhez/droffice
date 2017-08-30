package com.tacuadev.droffice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.HashMap;
import java.util.List;

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

    @GetMapping("/indexPatient")
    public ModelAndView indexPatient(HttpServletRequest request){

        ModelAndView mav = new ModelAndView("/patients/index");
        HashMap<String,String> patientAutocompleteMap = new HashMap<>();

        try {
            String parameterSearch = request.getParameter("search");
            LOG.info("parameterSearch: "+parameterSearch);
            List<PatientModel> patienModelFilterList = patientService.getPatientModel(parameterSearch);
            LOG.info("patienModelFilterList: "+patienModelFilterList);


            List<PatientModel> patientModelList = patientService.getAllPatientModel();
            ObjectMapper om = new ObjectMapper();


            for (PatientModel patientModel:patientModelList) {
                patientAutocompleteMap.put(patientModel.name,"");
                patientAutocompleteMap.put(patientModel.address,"");
                patientAutocompleteMap.put(patientModel.birthdayString,"");
//                patientAutocompleteMap.put(patientModel.sex,"");
            }

            String patientListJSONString = om.writeValueAsString(patientAutocompleteMap);
            LOG.info("patientListJSONString: "+patientListJSONString);

            mav.addObject("patientAutocompleteJSON",patientListJSONString);
            mav.addObject("patientsList",patientModelList);

        }catch (Throwable th){
            LOG.info(th.getMessage());
            LOG.info(th.getMessage(),th);
        }

        return mav;
    }

    @GetMapping("/createPatient")
    public ModelAndView createPatient(){
        return new ModelAndView("/patients/create");
    }

    @PostMapping("/savePatient")
    public void savePatient(HttpServletRequest request, HttpServletResponse response){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");

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
            String birthdayString = request.getParameter("birthday");
            try {
                Date openingDate = (Date) sdf.parse(openingDateString);
                Date birthday = (Date) sdf.parse(birthdayString);

                patientModel.openingDate = openingDate;
                patientModel.birthday = birthday;
            }catch (Throwable th){
                LOG.info(th.getMessage());
                LOG.info(th.getMessage(), th);
            }

            patientService.savePatient(patientModel);

        }catch (Throwable th){
            LOG.info(th.getMessage());
            LOG.info(th.getMessage(), th);
        }
    }

}
