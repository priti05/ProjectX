package org.projectX.dbo.login.dao.request;

public class InsertLoginInformationRequestTO {
	
	private String userProfileId;
	private String userName;
	private String password;
	
	public InsertLoginInformationRequestTO(String userProfileId,String userName, String password){
		this.userProfileId = userProfileId;
		this.userName = userName;
		this.password = password;
	}
	
	public String getUserProfileId() {
		return userProfileId;
	}
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}
	
	
	

}
