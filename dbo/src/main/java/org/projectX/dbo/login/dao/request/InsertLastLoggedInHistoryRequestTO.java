package org.projectX.dbo.login.dao.request;

public class InsertLastLoggedInHistoryRequestTO {
	
	private Long loginBiographyId;
	
	public InsertLastLoggedInHistoryRequestTO(Long loginBiographyId){
		this.loginBiographyId = loginBiographyId;
	}

	public Long getLoginBiographyId() {
		return loginBiographyId;
	}
	
	

}
