package org.projectX.dbo.profile.classLevelConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.projectX.dbo.profile.dto.UserProfile;
import org.projectX.dbo.utils.RegexValidatorUtils;

/**
 * <p><em>Support to annotation {@link ValidValidationMethod}</em>
 * Validate to see if {@link UserProfile} contains either 
 * valid email or phoneNumber</p></p>
 *
 * @author  Priti Patel
 * @see     org.projectX.dbo.profile.classLevelConstraint.ValidValidationMethod
 * @since   0.0.1
 */
public class ValidValidationMethodValidator implements ConstraintValidator<ValidValidationMethod, UserProfile> {

	private Logger logger = LogManager.getLogger(ValidValidationMethodValidator.class.getName());
	
	/**
     * have no code implementation
     *
     * @param  Take {@link ValidValidationMethod} as param
     * @since  0.0.1
     * @return void
     */
	public void initialize(ValidValidationMethod constraintAnnotation) {
		// TODO Auto-generated method stub
	}
	
	/**
     * This is the actual method that checks if {@link UserProfile} entity 
     * has valid email or phone number
     *
     * @param  {@link UserProfile} to check if it has valid foreign key relation
     * @param  {{@link ConstraintValidatorContext}
     * @since  0.0.1
     * @return boolean
     * 
     */
	public boolean isValid(UserProfile userProfile, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		logger.info("inside isValid::");
		if(userProfile!=null){
			RegexValidatorUtils regexValidatorUtils = new RegexValidatorUtils();
			if(regexValidatorUtils.phoneNumberValidator(userProfile.getPhoneNumber())|| regexValidatorUtils.emailValidator(userProfile.getEmailAddress())){
				if(logger.isDebugEnabled())
					logger.debug("Validated successfully");
				return true;
			}
		}
		return false;
	}

}
