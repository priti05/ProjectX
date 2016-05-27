package org.projectX.dbo.login.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.projectX.dbo.customException.ProjectXException;
import org.projectX.dbo.login.dao.helper.LoginDAOConstant;
import org.projectX.dbo.login.dao.helper.LoginDAOHelper;
import org.projectX.dbo.login.dao.request.InsertLastLoggedInHistoryRequestTO;
import org.projectX.dbo.login.dao.request.InsertLoginBiographyRequestTO;
import org.projectX.dbo.login.dao.request.InsertLoginInformationRequestTO;
import org.projectX.dbo.login.dao.response.InsertLastLoggedInHistoryResponseTO;
import org.projectX.dbo.login.dao.response.InsertLoginBiographyResponseTO;
import org.projectX.dbo.login.dao.response.InsertLoginInformationResponseTO;
import org.projectX.dbo.utils.ProjectXUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginDAOImplementation implements LoginDAO{
	
	
	@Autowired
	private ProjectXUtils projectXUtils;
	
	@Autowired
	private LoginDAOHelper loginDAOHelper;
	
	
	private Logger logger = LogManager.getLogger(LoginDAOImplementation.class);
	
	@Override
	public InsertLoginInformationResponseTO insertLoginInformation(
			InsertLoginInformationRequestTO insertLoginInformationRequestTO,Session session) throws ProjectXException{
		logger.info("Inside insertLoginInformation");
		InsertLoginInformationResponseTO insertLoginInformationResponseTO=null;
		try{
			insertLoginInformationResponseTO = loginDAOHelper.insertLoginInformationHelper(insertLoginInformationRequestTO,session);
		}catch(ProjectXException p){
			throw p;
		}catch(Exception e){
			logger.error("Exception at insertLoginInformation::", e.getMessage());
			logger.error("stack trace::", e);
			throw projectXUtils.genericError(LoginDAOConstant.PROJX_LGN_GEN_000, insertLoginInformationResponseTO.getProjXTrackId(), 
					LoginDAOConstant.INSERT_LOGIN_INFORMATION, null, session);
		}
		
		logger.info("End of insertLoginInformation");
		return insertLoginInformationResponseTO;
	}

	@Override
	public InsertLoginBiographyResponseTO insertLoginBiography(
			InsertLoginBiographyRequestTO insertLoginBiographyRequestTO, Session session) throws ProjectXException {
		logger.info("Inside insertLoginBiography");
		InsertLoginBiographyResponseTO insertLoginBiographyResponseTO = null;
		try{
			insertLoginBiographyResponseTO = loginDAOHelper.insertLoginBiographyHelper(insertLoginBiographyRequestTO,session);
		}catch(ProjectXException p){
			throw p;
		}catch(Exception e){
			logger.error("Exception at insertLoginBiography::", e.getMessage());
			logger.error("stack trace::", e);
			throw projectXUtils.genericError(LoginDAOConstant.PROJX_LGN_GEN_000, insertLoginBiographyResponseTO.getProjTrackId(), 
					LoginDAOConstant.INSERT_LOGIN_BIOGRAPHY, null, session);
		}
		logger.info("End of insertLoginBiography");
		return insertLoginBiographyResponseTO;
		
	}

	@Override
	public InsertLastLoggedInHistoryResponseTO insertLastLoggedInHistory(
			InsertLastLoggedInHistoryRequestTO insertLastLoggedInHistoryRequestTO, Session session)
					throws ProjectXException {
		logger.info("Inside insertLastLoggedInHistory");
		InsertLastLoggedInHistoryResponseTO insertLastLoggedInHistoryResponseTO = null;
		try{
			insertLastLoggedInHistoryResponseTO = loginDAOHelper.insertLastLoggedInHistoryHelper(insertLastLoggedInHistoryRequestTO,session);
		}catch(ProjectXException p){
			throw p;
		}catch(Exception e){
			logger.error("Exception at insertLastLoggedInHistory::", e.getMessage());
			logger.error("stack trace::", e);
			throw projectXUtils.genericError(LoginDAOConstant.PROJX_LGN_GEN_000, insertLastLoggedInHistoryResponseTO.getProjXTrckId(), 
					LoginDAOConstant.INSERT_LAST_LOGGEDID_HISTORY, null, session);
		}
		logger.info("End of insertLastLoggedInHistory");
		return insertLastLoggedInHistoryResponseTO;
	}
}
