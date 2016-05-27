package org.projectX.dbo.login.dao.response;

public class InsertLastLoggedInHistoryResponseTO {
	
	private String projXTrckId;
	private boolean inserted;
	
	public InsertLastLoggedInHistoryResponseTO(String projXTrckId){
		this.projXTrckId = projXTrckId;
	}

	public boolean isInserted() {
		return inserted;
	}

	public void setInserted(boolean inserted) {
		this.inserted = inserted;
	}

	public String getProjXTrckId() {
		return projXTrckId;
	}

	
	
}
