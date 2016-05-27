package org.projectX.dbo.profile.dao.response;

public class InsertVerificationMethodResponseTO {
	
	private String projXTrckId;
	private boolean inserted;
	
	public InsertVerificationMethodResponseTO(String projXTrckId){
		this.projXTrckId = projXTrckId;
	}
	
	public String getProjXTrckId() {
		return projXTrckId;
	}
	public boolean isInserted() {
		return inserted;
	}
	public void setInserted(boolean inserted) {
		this.inserted = inserted;
	}
	
	

}
