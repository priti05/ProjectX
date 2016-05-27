package org.projectX.dbo.profile.dao;

import org.hibernate.Session;
import org.projectX.dbo.customException.ProjectXException;
import org.projectX.dbo.profile.dao.request.InsertUserProfileRequestTO;
import org.projectX.dbo.profile.dao.request.InsertVerificationMethodRequestTO;
import org.projectX.dbo.profile.dao.response.InsertUserProfileResponseTO;
import org.projectX.dbo.profile.dao.response.InsertVerificationMethodResponseTO;

public interface ProfileDAO {
	public InsertUserProfileResponseTO insertUserProfile(
			InsertUserProfileRequestTO insertUserProfileRequestTO,Session session) throws ProjectXException;
	
	public InsertVerificationMethodResponseTO insertVerificationMethod(
			InsertVerificationMethodRequestTO insertVerificationMethodRequestTO,Session session) throws ProjectXException;
}
