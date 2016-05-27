package org.projectX.dbo.profile.dao.helper;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.projectX.dbo.common.dto.Occupation;
import org.projectX.dbo.customException.ProjectXException;
import org.projectX.dbo.enumHelper.RegistrationVerificationTypeEnum;
import org.projectX.dbo.profile.dao.ProfileDAOImplementations;
import org.projectX.dbo.profile.dao.request.InsertUserProfileRequestTO;
import org.projectX.dbo.profile.dao.request.InsertVerificationMethodRequestTO;
import org.projectX.dbo.profile.dao.response.InsertUserProfileResponseTO;
import org.projectX.dbo.profile.dao.response.InsertVerificationMethodResponseTO;
import org.projectX.dbo.profile.dto.UserProfile;
import org.projectX.dbo.profile.dto.VerificationMethod;
import org.projectX.dbo.utils.ProjectXUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>This class act as a helper class of {@link org.projectX.dbo.profile.dao.ProfileDAOImplementations}. 
 * Methods of this class are the one carrying core logic </p>
 * 
 * @author Priti Patel
 * @since 0.0.1
 * @see org.projectX.dbo.profile.dao.ProfileDAOImplementations
 * @see org.projectX.dbo.profile.dao.helper.ProfileDAOConstant
 *
 */
public class ProfileDAOHelper {

	private Logger logger = LogManager.getLogger(ProfileDAOHelper.class);
	
	@Autowired
	private ProjectXUtils projectXUtils;
	
	/**
	 * <p>This method is a helper method of {@link ProfileDAOImplementations#insertUserProfile(InsertUserProfileRequestTO, Session)}</p>
	 * 
	 * @param InsertUserProfileRequestTO
	 * @param Session
	 * @return InsertUserProfileResponseTO
	 * @throws ProjectXException
	 */
	public InsertUserProfileResponseTO insertProfileHelper(InsertUserProfileRequestTO insertUserProfileRequestTO,
			Session session) throws  ProjectXException {		
		logger.info("Inside insertProfileHelper");
		String trackingId = null;
		try{
			trackingId = projectXUtils.getTrackingId(insertUserProfileRequestTO,ProfileDAOConstant.INSERT_USER_PROFILE,session);
			InsertUserProfileResponseTO insertUserProfileResponseTO = new InsertUserProfileResponseTO(trackingId);
			UserProfile userProfile = null;
			if(insertUserProfileRequestTO!=null){
				String fName = insertUserProfileRequestTO.getFirstName();
				String lName = insertUserProfileRequestTO.getLastName();
				Integer occuId = insertUserProfileRequestTO.getOccupationId();
				String pNumber = insertUserProfileRequestTO.getPhoneNumber();
				String email = insertUserProfileRequestTO.getEmail();
				
				if(StringUtils.isNotBlank(fName) && StringUtils.isNotBlank(lName) &&
						(StringUtils.isNotBlank(pNumber) || StringUtils.isNotBlank(email))){
					userProfile = new UserProfile(fName,lName);
					userProfile.setMiddleName(insertUserProfileRequestTO.getMiddleName());
					userProfile.setNickName( insertUserProfileRequestTO.getNickName());
					userProfile.setSex(insertUserProfileRequestTO.getSex());
					userProfile.setPhoneNumber(pNumber);
					userProfile.setEmailAddress(email);
					userProfile.setEmployer(insertUserProfileRequestTO.getEmployer());
					userProfile.setOccupation((Occupation)projectXUtils.getObjectById(occuId, session, Occupation.class));
					session.persist(userProfile);
				}
			}
			if(userProfile!=null){
				insertUserProfileResponseTO.setInserted(true);
				insertUserProfileResponseTO.setUserProfileId(userProfile.getUserProfileId());
				projectXUtils.updateTrackingIdTableWithUser(trackingId, insertUserProfileResponseTO,session);
			}else{
				logger.warn("Invalid Input");
				throw projectXUtils.genericError(ProfileDAOConstant.PROJX_PROF_IVLD_000, trackingId, 
						ProfileDAOConstant.INSERT_USER_PROFILE, null, session);
			}
			logger.info("End of insertProfileHelper");
			return insertUserProfileResponseTO;
		}catch(Exception e){
			logger.error("Exception at genericError",e.getMessage());
			logger.error("stack traces::",e);
			throw projectXUtils.genericError(ProfileDAOConstant.PROJX_PROF_GEN_000, trackingId, 
					ProfileDAOConstant.INSERT_USER_PROFILE, null, session);
		}
	}

	public InsertVerificationMethodResponseTO insertVerificationMethodHelper(
			InsertVerificationMethodRequestTO insertVerificationMethodRequestTO, Session session)  throws  ProjectXException{
		logger.info("Inside insertVerificationMethodHelper");
		String trackingId = null;
		try{
			trackingId = projectXUtils.getTrackingId(insertVerificationMethodRequestTO,ProfileDAOConstant.INSERT_VERIFICATION_METHOD,session);
			InsertVerificationMethodResponseTO insertVerificationMethodResponseTO = new InsertVerificationMethodResponseTO(trackingId);
			VerificationMethod verificationMethod = null;
			if(insertVerificationMethodRequestTO!=null){
				String userProfileId = insertVerificationMethodRequestTO.getUserProfileId();
				RegistrationVerificationTypeEnum registrationVerificationType = 
						insertVerificationMethodRequestTO.getRegistrationVerificationType();
				String agrn = insertVerificationMethodRequestTO.getAgrn();
				if(StringUtils.isNotBlank(userProfileId) && StringUtils.isNotBlank(agrn) && 
						registrationVerificationType!=null){
					UserProfile userProfile = (UserProfile)projectXUtils.loadFromSession(userProfileId, session, UserProfile.class);
					verificationMethod = new VerificationMethod(registrationVerificationType,agrn , userProfile);
					userProfile.setVerificationMethod(verificationMethod);
					//session.persist(verificationMethod);
				}		
			}
			
			if(verificationMethod!=null){
				insertVerificationMethodResponseTO.setInserted(true);
				projectXUtils.updateTrackingIdTableWithUser(trackingId, insertVerificationMethodResponseTO,session);
			}else{
				logger.warn("Invalid Input");
				throw projectXUtils.genericError(ProfileDAOConstant.PROJX_PROF_IVLD_000, trackingId, 
						ProfileDAOConstant.INSERT_VERIFICATION_METHOD, null, session);
			}
			logger.info("End of insertVerificationMethodHelper");
			return insertVerificationMethodResponseTO;
		}catch(Exception e){
			logger.error("Exception at genericError",e.getMessage());
			logger.error("stack traces::",e);
			throw projectXUtils.genericError(ProfileDAOConstant.PROJX_PROF_GEN_000, trackingId, 
					ProfileDAOConstant.INSERT_VERIFICATION_METHOD, null, session);
		}
	}
	
	

}
