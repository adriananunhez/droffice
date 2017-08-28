package com.tacuadev.droffice.service;

import com.tacuadev.droffice.component.DataBaseUtils;
import com.tacuadev.droffice.model.PatientModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

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

    public void savePatient(PatientModel patientModel){
        Connection connection = null;
        PreparedStatement insertPatientPs = null;
        String insertPatientString = "INSERT INTO patient(name, document_number, address, sex, sex_int, birthday, opening_date, creation_date) VALUES (?, ?, ?, ?, ?, ?, ?, current_timestamp);";

        Timestamp birthdayTs = null;
        try{
            connection = dataSource.getConnection();
            insertPatientPs = connection.prepareStatement(insertPatientString);

            LOG.info("patientModel.birthday: "+patientModel.birthday);
            birthdayTs = (Timestamp) patientModel.birthday;

            insertPatientPs.setString(1,patientModel.name);
            insertPatientPs.setString(2,patientModel.documentNumber);
            insertPatientPs.setString(3,patientModel.address);
            insertPatientPs.setString(4,patientModel.sex);
            insertPatientPs.setInt(5,patientModel.sexInt);
            insertPatientPs.setTimestamp(6, birthdayTs);
            insertPatientPs.setTimestamp(7, (Timestamp) patientModel.openingDate);
            insertPatientPs.executeUpdate();

        }catch (Throwable th){
            LOG.info(th.getMessage());
            LOG.info(th.getMessage(),th);
        }finally {
            dataBaseUtils.preparedStatementClose(insertPatientPs);
        }

    }


}
