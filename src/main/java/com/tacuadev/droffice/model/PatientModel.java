package com.tacuadev.droffice.model;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by fd on 25/08/17.
 */
@Component("/patientModel")
public class PatientModel {

    public static int PATIENT_MALE = 1;
    public static int PATIENT_FEMALE = 2;

    public static String PATIENT_MALE_STRING = "male";
    public static String PATIENT_FEMALE_STRING = "female";

    public Long id;
    public Date openingDate;
    public String name;
    public String documentNumber;
    public String sex;
    public int sexInt;
    public Date birthday;
    public String address;
    public Date creationDate;
}
