package org.projectX.dbo.profile.dao.request;

import org.projectX.dbo.enumHelper.RegistrationVerificationTypeEnum;

public class InsertVerificationMethodRequestTO {
	
	private String userProfileId;
	private RegistrationVerificationTypeEnum registrationVerificationType;
	private String agrn;
	
	public InsertVerificationMethodRequestTO(String userProfileId, RegistrationVerificationTypeEnum registrationVerificationType,String agrn){
		this.userProfileId = userProfileId;
		this.registrationVerificationType = registrationVerificationType;
		this.agrn = agrn;
	}

	public String getUserProfileId() {
		return userProfileId;
	}

	public RegistrationVerificationTypeEnum getRegistrationVerificationType() {
		return registrationVerificationType;
	}

	public String getAgrn() {
		return agrn;
	}

	
	
}
