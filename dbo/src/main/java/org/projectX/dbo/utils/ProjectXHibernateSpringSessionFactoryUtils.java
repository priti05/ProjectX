package org.projectX.dbo.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.projectX.dbo.utils.InterFace.ProjectX;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public enum ProjectXHibernateSpringSessionFactoryUtils implements ProjectX {
	
	
	INSTANCE;
	
	private final SessionFactory sessionFactory=buildHibernateSessionFactory();
	private final AbstractApplicationContext applicationContext = buildAppContext();
	
	public final SessionFactory getSessionFactoryInstance(){
		return sessionFactory;
	}
	
	private final SessionFactory buildHibernateSessionFactory(){
		Logger logger = LogManager.getLogger(ProjectXHibernateSpringSessionFactoryUtils.class.getName());
		logger.info("inside buildHibernateSessionFactory");
		try{
		Configuration configuration = new Configuration();
	    configuration.configure("/configuration/hibernate/xml/projectX.cfg.xml");
	    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
	            configuration.getProperties()).build();
	    return configuration.buildSessionFactory(serviceRegistry);
		}catch(Throwable te){
			logger.fatal("Session Factory hasn't been initialized" + te.getMessage());
			logger.fatal("stackTrace::",te);
			logger.fatal("end of buildHibernateSessionFactory");
			throw new ExceptionInInitializerError(te);
		}
	}
	
	
	public final AbstractApplicationContext getApplicationContextInstance(){
		return applicationContext;
	}
	
	
	public final AbstractApplicationContext buildAppContext(){
		Logger logger = LogManager.getLogger(ProjectXHibernateSpringSessionFactoryUtils.class.getName());
		try{
			logger.info("Initializing ApplicationContext");
			AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext("/configuration/spring/xml/projectX-Spring-Context.xml");
			applicationContext.registerShutdownHook();
			return applicationContext;
		}catch(Throwable te){
			logger.fatal("ApplicationContext hasn't been initialized" + te.getMessage());
			logger.fatal("stackTrace::",te);
			logger.fatal("end of buildAppContext");
			throw new ExceptionInInitializerError(te);
		}
	}

}
