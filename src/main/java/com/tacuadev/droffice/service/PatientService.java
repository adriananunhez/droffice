package com.tacuadev.droffice.service;

import com.tacuadev.droffice.component.DataBaseUtils;
import com.tacuadev.droffice.model.PatientMedicalHistoryModel;
import com.tacuadev.droffice.model.PatientModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by fd on 23/08/17.
 */
@Service("patientService")
public class PatientService {
    private static final Log LOG = LogFactory.getLog(PatientService.class);

    @Autowired
    @Qualifier("dataBaseUtil")
    DataBaseUtils dataBaseUtils;

    @Autowired
    DataSource dataSource;

    public List getAllPatientModel(){
        PatientModel patientModel = null;
        List<PatientModel> patientModelList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Connection connection = null;
        PreparedStatement selectPatientPs = null;
        ResultSet selectPatientRs = null;
        String selectPatientString = "SELECT id, address, birthday, creation_date, document_number, name, opening_date, sex, sex_int FROM patient ORDER BY name ASC;";

        Date birthdayDate;
        Date openingDate;
        try{
            connection = dataSource.getConnection();
            selectPatientPs = connection.prepareStatement(selectPatientString);
            selectPatientRs = selectPatientPs.executeQuery();
            while (selectPatientRs.next()){
                patientModel = new PatientModel();
                birthdayDate = new Date(selectPatientRs.getTimestamp("birthday").getTime());
                openingDate = new Date(selectPatientRs.getTimestamp("opening_date").getTime());

                patientModel.id = selectPatientRs.getLong("id");
                patientModel.address = selectPatientRs.getString("address");
                patientModel.documentNumber = selectPatientRs.getString("document_number");
                patientModel.name = selectPatientRs.getString("name");
                patientModel.sex = selectPatientRs.getString("sex");
                patientModel.sexInt = selectPatientRs.getInt("sex_int");
                patientModel.birthday = birthdayDate;
                patientModel.birthdayString = sdf.format(birthdayDate);
                patientModel.openingDate = openingDate;
                patientModel.openingDateString = sdf.format(openingDate);
                patientModel.creationDate = new Date(selectPatientRs.getTimestamp("creation_date").getTime());
                patientModelList.add(patientModel);
            }
        }catch (Throwable th){
            LOG.info(th.getMessage());
            LOG.info(th.getMessage(), th);
        }finally {
            dataBaseUtils.preparedStatementClose(selectPatientPs);
            dataBaseUtils.resultSetClose(selectPatientRs);
        }

        return patientModelList;
    }

    public List getPatientModel(String patientSearchValue){
        PatientModel patientModel = null;
        List<PatientModel> patientModelFilterList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Connection connection = null;
        PreparedStatement selectPatientPs = null;
        ResultSet selectPatientRs = null;
        StringBuilder selectPatientSb = new StringBuilder();
        selectPatientSb.append("SELECT id, address, birthday, creation_date, document_number, name, opening_date, sex, sex_int FROM patient");
        selectPatientSb.append(" WHERE UPPER(name) like ? OR UPPER(address) like ? ORDER BY name ASC;");

        Date birthdayDate;
        Date openingDate;
        try{
            connection = dataSource.getConnection();
            selectPatientPs = connection.prepareStatement(selectPatientSb.toString());
            selectPatientPs.setString(1,"%"+patientSearchValue.toUpperCase()+"%");
            selectPatientPs.setString(2,"%"+patientSearchValue.toUpperCase()+"");

            selectPatientRs = selectPatientPs.executeQuery();
            while (selectPatientRs.next()){
                patientModel = new PatientModel();
                birthdayDate = new Date(selectPatientRs.getTimestamp("birthday").getTime());
                openingDate = new Date(selectPatientRs.getTimestamp("opening_date").getTime());

                patientModel.id = selectPatientRs.getLong("id");
                patientModel.address = selectPatientRs.getString("address");
                patientModel.documentNumber = selectPatientRs.getString("document_number");
                patientModel.name = selectPatientRs.getString("name");
                patientModel.sex = selectPatientRs.getString("sex");
                patientModel.sexInt = selectPatientRs.getInt("sex_int");
                patientModel.birthday = birthdayDate;
                patientModel.birthdayString = sdf.format(birthdayDate);
                patientModel.openingDate = openingDate;
                patientModel.openingDateString = sdf.format(openingDate);
                patientModel.creationDate = new Date(selectPatientRs.getTimestamp("creation_date").getTime());
                patientModelFilterList.add(patientModel);
            }
        }catch (Throwable th){
            LOG.info(th.getMessage());
            LOG.info(th.getMessage(), th);
        }finally {
            dataBaseUtils.preparedStatementClose(selectPatientPs);
            dataBaseUtils.resultSetClose(selectPatientRs);
        }

        return patientModelFilterList;
    }

    public void savePatient(PatientModel patientModel){
        Connection connection = null;
        PreparedStatement insertPatientPs = null;
        String insertPatientString = "INSERT INTO patient(name, document_number, address, sex, sex_int, birthday, opening_date, creation_date) VALUES (?, ?, ?, ?, ?, ?, ?, current_timestamp);";

        Timestamp birthdayTs = null;
        Timestamp openingDateTs = null;
        try{
            connection = dataSource.getConnection();
            insertPatientPs = connection.prepareStatement(insertPatientString);

            birthdayTs = new Timestamp(patientModel.birthday.getTime());
            openingDateTs = new Timestamp(patientModel.openingDate.getTime());

            insertPatientPs.setString(1,patientModel.name);
            insertPatientPs.setString(2,patientModel.documentNumber);
            insertPatientPs.setString(3,patientModel.address);
            insertPatientPs.setString(4,patientModel.sex);
            insertPatientPs.setInt(5,patientModel.sexInt);
            insertPatientPs.setTimestamp(6, birthdayTs);
            insertPatientPs.setTimestamp(7, openingDateTs);
            insertPatientPs.executeUpdate();

        }catch (Throwable th){
            LOG.info(th.getMessage());
            LOG.info(th.getMessage(),th);
        }finally {
            dataBaseUtils.preparedStatementClose(insertPatientPs);
        }

    }

    public PatientModel getPatientModelById(long patientId){
        PatientModel patientModel = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Connection connection = null;
        PreparedStatement selectPatientPs = null;
        ResultSet selectPatientRs = null;
        StringBuilder selectPatientSb = new StringBuilder();
        selectPatientSb.append("SELECT id, address, birthday, creation_date, document_number, name, opening_date, sex, sex_int FROM patient");
        selectPatientSb.append(" WHERE id = ? ORDER BY name ASC;");

        Date birthdayDate;
        Date openingDate;
        try{
            connection = dataSource.getConnection();
            selectPatientPs = connection.prepareStatement(selectPatientSb.toString());
            selectPatientPs.setLong(1,patientId);

            selectPatientRs = selectPatientPs.executeQuery();
            if (selectPatientRs.next()){
                patientModel = new PatientModel();
                birthdayDate = new Date(selectPatientRs.getTimestamp("birthday").getTime());
                openingDate = new Date(selectPatientRs.getTimestamp("opening_date").getTime());

                patientModel.id = selectPatientRs.getLong("id");
                patientModel.address = selectPatientRs.getString("address");
                patientModel.documentNumber = selectPatientRs.getString("document_number");
                patientModel.name = selectPatientRs.getString("name");
                patientModel.sex = selectPatientRs.getString("sex");
                patientModel.sexInt = selectPatientRs.getInt("sex_int");
                patientModel.birthday = birthdayDate;
                patientModel.birthdayString = sdf.format(birthdayDate);
                patientModel.openingDate = openingDate;
                patientModel.openingDateString = sdf.format(openingDate);
                patientModel.creationDate = new Date(selectPatientRs.getTimestamp("creation_date").getTime());
                patientModel.patientMedicalHistoryModelList = getPatientHistoryModelList(patientId);
            }


        }catch (Throwable th){
            LOG.info(th.getMessage());
            LOG.info(th.getMessage(), th);
        }finally {
            dataBaseUtils.preparedStatementClose(selectPatientPs);
            dataBaseUtils.resultSetClose(selectPatientRs);
        }

        return patientModel;
    }

    public List<PatientMedicalHistoryModel> getPatientHistoryModelList(long patientId){
        List<PatientMedicalHistoryModel> patientMedicalHistoryModelList = new ArrayList<>();
        PatientMedicalHistoryModel patientMedicalHistoryModel = null;

        Connection connection = null;
        PreparedStatement patientMedicalHistoryPs = null;
        ResultSet patientMedicalHistoryRs = null;
        StringBuilder patientMedicalHistorySb = new StringBuilder();
        patientMedicalHistorySb.append("SELECT id, row_number, date, diagnostic, prescription, symptom, place_attention_id, creation_date");
        patientMedicalHistorySb.append(" FROM patient_medical_history WHERE patient_id = ?");
        try{
            connection = dataSource.getConnection();
            patientMedicalHistoryPs = connection.prepareStatement(patientMedicalHistorySb.toString());
            patientMedicalHistoryPs.setLong(1,patientId);
            patientMedicalHistoryRs = patientMedicalHistoryPs.executeQuery();


            while (patientMedicalHistoryRs.next()){
                patientMedicalHistoryModel = new PatientMedicalHistoryModel();
                patientMedicalHistoryModel.id = patientMedicalHistoryRs.getLong("id");
                patientMedicalHistoryModel.rowNumber = patientMedicalHistoryRs.getInt("row_number");
                patientMedicalHistoryModel.date = new Date(patientMedicalHistoryRs.getTimestamp("date").getTime());
                patientMedicalHistoryModel.diagnostic = patientMedicalHistoryRs.getString("diagnostic");
                patientMedicalHistoryModel.prescription = patientMedicalHistoryRs.getString("symptom");
                patientMedicalHistoryModel.symptom = patientMedicalHistoryRs.getString("symptom");
                //patientMedicalHistoryModel.placeAttentionModel.id = patientMedicalHistoryRs.getLong("place_attention_id");
                patientMedicalHistoryModel.creationDate = new Date(patientMedicalHistoryRs.getTimestamp("creation_date").getTime());
                patientMedicalHistoryModelList.add(patientMedicalHistoryModel);
            }
        }catch (Throwable th){
            LOG.info(th.getMessage());
            LOG.info(th.getMessage(), th);
        }finally {
            dataBaseUtils.preparedStatementClose(patientMedicalHistoryPs);
            dataBaseUtils.resultSetClose(patientMedicalHistoryRs);
        }
        return patientMedicalHistoryModelList;
    }
}
