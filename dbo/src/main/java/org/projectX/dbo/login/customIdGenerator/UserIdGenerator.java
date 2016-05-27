package org.projectX.dbo.login.customIdGenerator;

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

public class UserIdGenerator implements IdentifierGenerator {

	private final String USER_ID_SEQEUNCE = "userId_seqeunce";
	private Logger logger = LogManager.getLogger(UserIdGenerator.class);
	
    public Serializable generate(SessionImplementor sessionImpl, Object data)
            throws HibernateException {
    	logger.info("Inside generate");
        Serializable suffix = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            BigInteger prefix = new BigInteger("100");
            connection = sessionImpl.connection();
            statement = connection.createStatement();                   
             try {  
                 resultSet = statement.executeQuery("SELECT " +USER_ID_SEQEUNCE+".nextval FROM DUAL");
             } catch(Exception ex) {
                 // if sequence is not found then creating the sequence
            	 logger.error("stack trace::",ex);
                 statement = connection.createStatement();
                 statement.execute("CREATE SEQUENCE "+USER_ID_SEQEUNCE +" START WITH 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 CYCLE");
                 logger.warn("CREATE SEQUENCE "+USER_ID_SEQEUNCE +" START WITH 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 CYCLE");
                 resultSet = statement.executeQuery("SELECT " +USER_ID_SEQEUNCE+".nextval FROM DUAL");
             }
            
            if(resultSet.next()) { 
            	DateFormat df = new SimpleDateFormat("ssyyyymmddHHMM");
            	BigInteger nextValue = new BigInteger(String.valueOf(resultSet.getInt(1))) ;
                suffix = nextValue.add(prefix) + df.format(new Date(System.currentTimeMillis()));
            }
        } catch (SQLException e) {
        	logger.error("Exception at generate::", e.getMessage());
        	logger.error("stack Traces::" , e);
        }
        if(logger.isDebugEnabled())
        	logger.debug("generated USER_ID::" + suffix.toString());
        logger.info("End of generate");
        return suffix;
    }

}
