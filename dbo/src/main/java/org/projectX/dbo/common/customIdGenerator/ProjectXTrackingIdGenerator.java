package org.projectX.dbo.common.customIdGenerator;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class ProjectXTrackingIdGenerator implements IdentifierGenerator{

	private final String PROJECTXTRCKID_SEQUENCE = "ProjectXTrckId_Sequence";
	private Logger logger = LogManager.getLogger(ProjectXTrackingIdGenerator.class);
	
	public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
		logger.info("Inside generate");
		Serializable suffix = null;
		String projectX = "PROJECT_X_";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            BigInteger prefix = new BigInteger("10000000");
            connection = session.connection();
            statement = connection.createStatement();                   
             try {  
            	 String exe = "SELECT " +PROJECTXTRCKID_SEQUENCE+".nextval FROM DUAL" ;
                 resultSet = statement.executeQuery(exe);
             } catch(Exception ex) {
                 // if sequence is not found then creating the sequence
            	 logger.error("stack trace::",ex);
                 statement = connection.createStatement();
                 statement.execute("CREATE SEQUENCE "+PROJECTXTRCKID_SEQUENCE +" START WITH 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 CYCLE");
                 logger.warn("CREATE SEQUENCE "+PROJECTXTRCKID_SEQUENCE +" START WITH 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 CYCLE");
                 resultSet = statement.executeQuery("SELECT " +PROJECTXTRCKID_SEQUENCE+".nextval FROM DUAL");
             }
            
            if(resultSet.next()) {
            	DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                 suffix = projectX + prefix.add(new BigInteger(String.valueOf(resultSet.getObject(1)))) + df.format(new Date(System.currentTimeMillis()));
            }
        } catch (SQLException e) {
        	logger.error("Exception at generate::", e.getMessage());
        	logger.error("stack Traces::" , e);
        }
        if(logger.isDebugEnabled())
        	logger.debug("generated PROJECT_X_TRK_ID::" + suffix.toString());
        logger.info("End of generate");
        return suffix;
	}
	
	

}

