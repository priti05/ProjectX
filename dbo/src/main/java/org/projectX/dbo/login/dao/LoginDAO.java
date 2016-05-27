package org.projectX.dbo.login.dao;

import org.hibernate.Session;
import org.projectX.dbo.customException.ProjectXException;
import org.projectX.dbo.login.dao.request.InsertLastLoggedInHistoryRequestTO;
import org.projectX.dbo.login.dao.request.InsertLoginBiographyRequestTO;
import org.projectX.dbo.login.dao.request.InsertLoginInformationRequestTO;
import org.projectX.dbo.login.dao.response.InsertLastLoggedInHistoryResponseTO;
import org.projectX.dbo.login.dao.response.InsertLoginBiographyResponseTO;
import org.projectX.dbo.login.dao.response.InsertLoginInformationResponseTO;

public interface LoginDAO {
	
	public InsertLoginInformationResponseTO insertLoginInformation(InsertLoginInformationRequestTO insertLoginInformationRequestTO
			,Session session) throws ProjectXException;

	public InsertLoginBiographyResponseTO insertLoginBiography(InsertLoginBiographyRequestTO insertLoginBiographyRequestTO
			,Session session) throws ProjectXException;
	
	public InsertLastLoggedInHistoryResponseTO insertLastLoggedInHistory(InsertLastLoggedInHistoryRequestTO insertLastLoggedInHistoryRequestTO
			,Session session) throws ProjectXException;
}
