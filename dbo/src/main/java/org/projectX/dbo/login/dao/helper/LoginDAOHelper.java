package org.projectX.dbo.login.dao.helper;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.projectX.dbo.customException.ProjectXException;
import org.projectX.dbo.login.dao.request.InsertLastLoggedInHistoryRequestTO;
import org.projectX.dbo.login.dao.request.InsertLoginBiographyRequestTO;
import org.projectX.dbo.login.dao.request.InsertLoginInformationRequestTO;
import org.projectX.dbo.login.dao.response.InsertLastLoggedInHistoryResponseTO;
import org.projectX.dbo.login.dao.response.InsertLoginBiographyResponseTO;
import org.projectX.dbo.login.dao.response.InsertLoginInformationResponseTO;
import org.projectX.dbo.login.dto.LastLoggedInHistory;
import org.projectX.dbo.login.dto.LoginBiography;
import org.projectX.dbo.login.dto.LoginInformation;
import org.projectX.dbo.profile.dto.UserProfile;
import org.projectX.dbo.utils.ProjectXUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginDAOHelper {

	private Logger logger = LogManager.getLogger(LoginDAOHelper.class);
	
	@Autowired
	private ProjectXUtils projectXUtils;
	
	public InsertLoginInformationResponseTO insertLoginInformationHelper(
			InsertLoginInformationRequestTO insertLoginInformationRequestTO, Session session) throws ProjectXException{
		logger.info("Inside insertLoginInformationHelper");
		String trackingId = null;
		try{
			trackingId = projectXUtils.getTrackingId(insertLoginInformationRequestTO,LoginDAOConstant.INSERT_LOGIN_INFORMATION,session);
			InsertLoginInformationResponseTO loginInformationResponseTO = new InsertLoginInformationResponseTO(trackingId);
			LoginInformation loginInformation = null;
			if(insertLoginInformationRequestTO!=null){
				String userProfileId = insertLoginInformationRequestTO.getUserProfileId();
				String userName = insertLoginInformationRequestTO.getUserName();
				String password = insertLoginInformationRequestTO.getPassword();
				if(StringUtils.isNotBlank(userProfileId) && StringUtils.isNotBlank(userName) &&
						StringUtils.isNotBlank(password)){
					UserProfile userProfile = (UserProfile)projectXUtils.loadFromSession(userProfileId, session, UserProfile.class);
					if(userProfile!=null){
						loginInformation = new LoginInformation(userProfile,userName,password);
						session.persist(loginInformation);
					}	
				}	
			}

			if(loginInformation!=null){
				loginInformationResponseTO.setInserted(true);
				loginInformationResponseTO.setUserId(loginInformation.getUserId());
				projectXUtils.updateTrackingIdTableWithUser(trackingId,loginInformationResponseTO,session);
			}else{
				logger.warn("Invalid Input");
				throw projectXUtils.genericError(LoginDAOConstant.PROJX_LGN_IVLD_000, trackingId, 
						LoginDAOConstant.INSERT_LOGIN_INFORMATION, null, session);
			}
			
			logger.info("End of insertLoginInformationHelper");
			return loginInformationResponseTO;
		}catch(Exception e){
			logger.error("Exception at insertLoginInformationHelper",e.getMessage());
			logger.error("stack traces::",e);
			throw projectXUtils.genericError(LoginDAOConstant.PROJX_LGN_GEN_000, trackingId, 
					LoginDAOConstant.INSERT_LOGIN_INFORMATION, null, session);
		}
	}

	public InsertLoginBiographyResponseTO insertLoginBiographyHelper(
			InsertLoginBiographyRequestTO insertLoginBiographyRequestTO, Session session) throws ProjectXException {
		logger.info("Inside insertLoginBiographyHelper");
		String trackingId = null;
		try{
			trackingId = projectXUtils.getTrackingId(insertLoginBiographyRequestTO,LoginDAOConstant.INSERT_LOGIN_BIOGRAPHY,session);
			InsertLoginBiographyResponseTO insertLoginBiographyResponseTO = new InsertLoginBiographyResponseTO(trackingId);
			LoginBiography loginBiography = null;
			if(insertLoginBiographyRequestTO!=null){
				if(StringUtils.isNotBlank(insertLoginBiographyRequestTO.getUserId())){
					LoginInformation loginInformation= (LoginInformation)projectXUtils.loadFromSession(
							insertLoginBiographyRequestTO.getUserId(), session, LoginInformation.class);
					if(loginInformation!=null){
						loginBiography = new LoginBiography(loginInformation);
						session.persist(loginBiography);
					}
				}
			}
			
			if(loginBiography!=null){
				insertLoginBiographyResponseTO.setInserted(true);
				insertLoginBiographyResponseTO.setLoginBiographyId(loginBiography.getLoginBiographyId());
				projectXUtils.updateTrackingIdTableWithUser(trackingId,insertLoginBiographyResponseTO,session);
			}else{
				logger.warn("Invalid Input");
				throw projectXUtils.genericError(LoginDAOConstant.PROJX_LGN_IVLD_000, trackingId, 
						LoginDAOConstant.INSERT_LOGIN_BIOGRAPHY, null, session);
			}
			logger.info("End of insertLoginBiographyHelper");
			return insertLoginBiographyResponseTO;
		}catch(Exception e){
			logger.error("Exception at insertLoginBiographyHelper",e.getMessage());
			logger.error("stack traces::",e);
			throw projectXUtils.genericError(LoginDAOConstant.PROJX_LGN_GEN_000, trackingId, 
					LoginDAOConstant.INSERT_LOGIN_BIOGRAPHY, null, session);
		}
		
	}

	public InsertLastLoggedInHistoryResponseTO insertLastLoggedInHistoryHelper(
			InsertLastLoggedInHistoryRequestTO insertLastLoggedInHistoryRequestTO, Session session) throws ProjectXException {
		logger.info("Inside insertLastLoggedInHistoryHelper");
		String trackingId = null;
		try{
			trackingId = projectXUtils.getTrackingId(insertLastLoggedInHistoryRequestTO,LoginDAOConstant.INSERT_LAST_LOGGEDID_HISTORY,session);
			InsertLastLoggedInHistoryResponseTO insertLastLoggedInHistoryResponseTO = new InsertLastLoggedInHistoryResponseTO(trackingId);
			LastLoggedInHistory lastLoggedInHistory = null;
			if(insertLastLoggedInHistoryRequestTO!=null){
				if(insertLastLoggedInHistoryRequestTO.getLoginBiographyId()!=null){
					LoginBiography loginBiography= (LoginBiography)projectXUtils.loadFromSession(
							insertLastLoggedInHistoryRequestTO.getLoginBiographyId(), session, LoginBiography.class);
					if(loginBiography!=null){
						lastLoggedInHistory = new LastLoggedInHistory(loginBiography);
						session.persist(lastLoggedInHistory);
					}
				}
			}
			
			if(lastLoggedInHistory!=null){
				insertLastLoggedInHistoryResponseTO.setInserted(true);
				projectXUtils.updateTrackingIdTableWithUser(trackingId,insertLastLoggedInHistoryResponseTO,session);
			}else{
				logger.warn("Invalid Input");
				throw projectXUtils.genericError(LoginDAOConstant.PROJX_LGN_IVLD_000, trackingId, 
						LoginDAOConstant.INSERT_LAST_LOGGEDID_HISTORY, null, session);
			}
			logger.info("End of insertLastLoggedInHistoryHelper");
			return insertLastLoggedInHistoryResponseTO;
		}catch(Exception e){
			logger.error("Exception at insertLastLoggedInHistoryHelper",e.getMessage());
			logger.error("stack traces::",e);
			throw projectXUtils.genericError(LoginDAOConstant.PROJX_LGN_GEN_000, trackingId, 
					LoginDAOConstant.INSERT_LAST_LOGGEDID_HISTORY, null, session);
		}
	}

}
