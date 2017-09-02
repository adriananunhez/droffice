package com.tacuadev.droffice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tacuadev.droffice.model.PatientMedicalHistoryModel;
import com.tacuadev.droffice.model.PatientModel;
import com.tacuadev.droffice.model.PlaceAttentionModel;
import com.tacuadev.droffice.service.PatientService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    @GetMapping("/index")
    public ModelAndView index(HttpServletRequest request){

        ModelAndView mav = new ModelAndView("/patients/index");
        HashMap<String,String> patientAutocompleteMap = new HashMap<>();
        List<PatientModel> patienModelFilterList = new ArrayList<>();
        try {
            String parameterSearch = "";
            parameterSearch = request.getParameter("search");
            LOG.info("parameterSearch: "+parameterSearch);

            if(parameterSearch == null){
                parameterSearch = "";
            }

            patienModelFilterList = patientService.getPatientModel(parameterSearch);


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
            mav.addObject("patientsList",patienModelFilterList);
            mav.addObject("parameterSearch",parameterSearch);

        }catch (Throwable th){
            LOG.info(th.getMessage());
            LOG.info(th.getMessage(),th);
        }

        return mav;
    }

    @GetMapping("/create")
    public ModelAndView create(){
        return new ModelAndView("/patients/create");
    }

    @PostMapping("/save")
    public void save(HttpServletRequest request, HttpServletResponse response){
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

        try {
            response.sendRedirect("/patients/show/"+1);
        } catch (Throwable th) {
            LOG.info(th.getMessage());
            LOG.info(th.getMessage(), th);
        }

    }

    @GetMapping("/show/{patientId}")
    public ModelAndView show(@PathVariable("patientId") long patientId){
        PatientModel patientModel = patientService.getPatientModelById(patientId);
        List<PlaceAttentionModel> placeAttentionModelList = patientService.getPlaceAttentionModelList();


        ModelAndView mav = new ModelAndView("/patients/show");
        mav.addObject("patientModel",patientModel);
        mav.addObject("placeAttentionList", placeAttentionModelList);
        return mav;
    }

    @GetMapping("/cancel")
    public void cancel(HttpServletResponse response){
        try {
            response.sendRedirect("/patients/index");
        } catch (Throwable th) {
            LOG.info(th.getMessage());
            LOG.info(th.getMessage(), th);
        }
    }

    @PostMapping("/savePatientHistory")
    public void savePatientHistory(HttpServletRequest request, HttpServletResponse response) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        String historyDateString = request.getParameter("history_date");
        Long patientIdLong = Long.valueOf(request.getParameter("patientId"));

        PatientMedicalHistoryModel patientMedicalHistoryModel = new PatientMedicalHistoryModel();

        try {
            patientMedicalHistoryModel.rowNumber = 1;//aca tiene que obtener el ultimo number del detalle
            patientMedicalHistoryModel.date = (Date) sdf.parse(historyDateString);
            patientMedicalHistoryModel.symptom = request.getParameter("sympton_textarea");
            patientMedicalHistoryModel.diagnostic = request.getParameter("diagnostic_textarea");
            patientMedicalHistoryModel.prescription = request.getParameter("prescription_textarea");
            patientMedicalHistoryModel.patientModel = patientService.getPatientModelById(patientIdLong);
            //patientMedicalHistoryModel.placeAttentionModel //falta agregar esta opcion en la vista.


            patientService.savePatientMedicalHistory(patientMedicalHistoryModel);
            response.sendRedirect("/patients/show/"+patientIdLong);

        } catch (Throwable th) {
            LOG.info(th.getMessage());
            LOG.info(th.getMessage(), th);
        }

    }

}
