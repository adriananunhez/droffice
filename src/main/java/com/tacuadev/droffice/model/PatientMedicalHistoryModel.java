package com.tacuadev.droffice.model;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by fd on 30/08/17.
 */
@Component("/patientMedicalHistoryModel")
public class PatientMedicalHistoryModel {
    public Long id;
    public Date date;
    public String dateString;
    public Integer rowNumber;
    public String symptom;
    public String diagnostic;
    public String prescription;
    public Date creationDate;
    public PlaceAttentionModel placeAttentionModel;
    public PatientModel patientModel;
}
