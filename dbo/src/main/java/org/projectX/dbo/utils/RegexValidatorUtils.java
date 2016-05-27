package org.projectX.dbo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegexValidatorUtils {
	
	private Logger logger = LogManager.getLogger(RegexValidatorUtils.class);
			
	public boolean phoneNumberValidator(String phoneNumber){
		logger.info("inside phoneNumberValidator");
		if(StringUtils.isNotBlank(phoneNumber)){
			Pattern pattern = Pattern.compile(UtilsConstants.phoneNumberRegex);
		    Matcher matcher = pattern.matcher(phoneNumber);
		    if(matcher.matches()){
		    	if(logger.isDebugEnabled())
		    		logger.debug("phoneNumber successfully validated");
		    	return true;
		    }
		}
		return false;
	}
	
	public boolean emailValidator(String email){
		logger.info("inside emailValidator");
		if(StringUtils.isNotBlank(email)){
			Pattern pattern = Pattern.compile(UtilsConstants.emailRegex);
			Matcher matcher = pattern.matcher(email);
			if(matcher.matches()){
				if(logger.isDebugEnabled())
		    		logger.debug("email successfully validated");
				return true;
			}
		}
		return false;
	}

}
