package org.projectX.dbo.profile.customIdGenerator;

import java.io.Serializable;
import java.util.Properties;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class CustomUUIDGenerator implements IdentifierGenerator{

	private Logger logger = LogManager.getLogger(CustomUUIDGenerator.class);
	
	public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
		logger.info("Inside generate");
		try{
			Properties properties= new Properties();
			properties.load(getClass().getClassLoader().getResourceAsStream("projectX.properties"));
			String databaseServer = properties.getProperty("Database.Server");
			return UUID.randomUUID().toString() + databaseServer;
		}catch(Exception e){
			logger.error("Exception at generate::", e.getMessage());
        	logger.error("stack Traces::" , e);
		}
		logger.info("End of Generate");
		return null;
	}

}
