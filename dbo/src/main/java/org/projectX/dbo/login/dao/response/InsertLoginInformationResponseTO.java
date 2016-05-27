package org.projectX.dbo.login.dao.response;

public class InsertLoginInformationResponseTO {
	
	private String userId;
	private String projXTrackId;
	private boolean inserted;
	
	public InsertLoginInformationResponseTO(String projXTrackId){
		this.projXTrackId=projXTrackId;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public boolean isInserted() {
		return inserted;
	}
	public void setInserted(boolean inserted) {
		this.inserted = inserted;
	}
	public String getProjXTrackId() {
		return projXTrackId;
	}
	
	

}
