package org.projectX.dbo.login.dao.response;

public class InsertLoginBiographyResponseTO {

	private String projTrackId;
	private boolean inserted;
	private Long loginBiographyId;
	
	public InsertLoginBiographyResponseTO(String projTrackId){
		this.projTrackId=projTrackId;
	}

	public boolean isInserted() {
		return inserted;
	}

	public void setInserted(boolean inserted) {
		this.inserted = inserted;
	}

	public Long getLoginBiographyId() {
		return loginBiographyId;
	}

	public void setLoginBiographyId(Long loginBiographyId) {
		this.loginBiographyId = loginBiographyId;
	}

	public String getProjTrackId() {
		return projTrackId;
	}
	
	
	
	
}
