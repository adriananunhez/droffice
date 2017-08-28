package com.tacuadev.droffice.component;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component("dataBaseUtil")
public class DataBaseUtils {

    private static final Log LOG = LogFactory.getLog(DataBaseUtils.class);

    public void resultSetClose(ResultSet resultSet){
        try {
            if(resultSet != null)
                resultSet.close();
        } catch(SQLException e) {
            LOG.error(e.toString());
            LOG.error(e.toString(), e);
        }
    }

    public void preparedStatementClose(PreparedStatement preparedStatement){
        try {
            if(preparedStatement != null)
                preparedStatement.close();
        } catch(SQLException e) {
            LOG.error(e.toString());
            LOG.error(e.toString(), e);
        }
    }

    public void connectionRelease(Connection connection, DataSource dataSource){
        try {
            if(connection != null)
                DataSourceUtils.releaseConnection(connection, dataSource);
        } catch(Throwable th) {
            LOG.error(th.toString());
            LOG.error(th.toString(), th);
        }
    }

    public void connectionRollback(Connection connection){
        try {
            if(connection != null)
                connection.rollback();
        } catch(Throwable th) {
            LOG.error(th.toString());
            LOG.error(th.toString(), th);
        }
    }
}
