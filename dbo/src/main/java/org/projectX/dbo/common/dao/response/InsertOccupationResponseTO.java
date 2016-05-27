package org.projectX.dbo.common.dao.response;

public class InsertOccupationResponseTO {
	
	private String projXTrackingId;
	
	public InsertOccupationResponseTO(String projXTrackingId){
		this.projXTrackingId = projXTrackingId;
	}
	
	private boolean inserted;

	public boolean isInserted() {
		return inserted;
	}

	public void setInserted(boolean inserted) {
		this.inserted = inserted;
	}

	public String getProjXTrackingId() {
		return projXTrackingId;
	}

}
