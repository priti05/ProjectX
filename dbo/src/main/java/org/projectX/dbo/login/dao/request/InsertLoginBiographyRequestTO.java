package org.projectX.dbo.login.dao.request;

public class InsertLoginBiographyRequestTO {
	
	private String userId;
	
	public InsertLoginBiographyRequestTO(String userId){
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}
	
	

}
