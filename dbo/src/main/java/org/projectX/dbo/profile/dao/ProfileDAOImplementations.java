package org.projectX.dbo.profile.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.projectX.dbo.customException.ProjectXException;
import org.projectX.dbo.profile.dao.helper.ProfileDAOConstant;
import org.projectX.dbo.profile.dao.helper.ProfileDAOHelper;
import org.projectX.dbo.profile.dao.request.InsertUserProfileRequestTO;
import org.projectX.dbo.profile.dao.request.InsertVerificationMethodRequestTO;
import org.projectX.dbo.profile.dao.response.InsertUserProfileResponseTO;
import org.projectX.dbo.profile.dao.response.InsertVerificationMethodResponseTO;
import org.projectX.dbo.utils.ProjectXUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>This is a entry point of ProjectX to perform CRUD operation on <em>User Profile entities</em></p>
 * 
 * @author Priti Patel
 * @since 0.0.1
 * @see org.projectX.dbo.profile.dao.helper.ProfileDAOHelper
 * @see org.projectX.dbo.profile.dao.helper.ProfileDAOConstant
 */
public class ProfileDAOImplementations implements ProfileDAO{
	
	@Autowired
	private ProjectXUtils projectXUtils;
	
	@Autowired
	private ProfileDAOHelper profileDAOHelper;
	
	private Logger logger = LogManager.getLogger(ProfileDAOImplementations.class);
	
	/**
	 * <p>This method is entry point of ProjectX to insert user profile details.<p>
	 * @param InsertUserProfileRequestTO
	 * @param Session
	 * @return InsertUserProfileResponseTO
	 * @throws ProjectXException
	 */
	public InsertUserProfileResponseTO insertUserProfile(InsertUserProfileRequestTO insertUserProfileRequestTO,Session session) throws ProjectXException{
		logger.info("Inside insertUserProfile");
			InsertUserProfileResponseTO insertUserProfileResponseTO = null;
			try{
				insertUserProfileResponseTO = profileDAOHelper.insertProfileHelper(insertUserProfileRequestTO,session);
			}catch(ProjectXException p){
				throw p;
			}catch(Exception e){
				logger.error("Exception at insertUserProfile::", e.getMessage());
				logger.error("stack trace::", e);
				throw projectXUtils.genericError(ProfileDAOConstant.PROJX_PROF_GEN_000, insertUserProfileResponseTO.getProjXTrackingId(), 
						ProfileDAOConstant.INSERT_USER_PROFILE, null, session);
			}
			logger.info("End of insertUserProfile");
		return insertUserProfileResponseTO;
	}

	@Override
	public InsertVerificationMethodResponseTO insertVerificationMethod(
			InsertVerificationMethodRequestTO insertVerificationMethodRequestTO, Session session)
					throws ProjectXException {
		logger.info("Inside insertVerificationMethod");
		InsertVerificationMethodResponseTO insertVerificationMethodResponseTO = null;
		try{
			insertVerificationMethodResponseTO = profileDAOHelper.insertVerificationMethodHelper(insertVerificationMethodRequestTO,session);
		}catch(ProjectXException p){
			throw p;
		}catch(Exception e){
			logger.error("Exception at insertVerificationMethod::", e.getMessage());
			logger.error("stack trace::", e);
			throw projectXUtils.genericError(ProfileDAOConstant.PROJX_PROF_GEN_000, insertVerificationMethodResponseTO.getProjXTrckId(), 
					ProfileDAOConstant.INSERT_VERIFICATION_METHOD, null, session);
		}
		logger.info("End of insertVerificationMethod");
		return insertVerificationMethodResponseTO;
	}
	

}
