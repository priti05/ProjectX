package org.projectX.dbo.profile.dao.response;

/**
 * <p>This is a response class to perform Create operation on <em>User Profile</em></p>
 * @author Priti Patel
 * @since 0.0.1
 * @see org.projectX.dbo.profile.dao.ProfileDAOImplementations
 */
public class InsertUserProfileResponseTO {

	private String projXTrackingId;
	private boolean inserted;
	private String userProfileId;
	
	
	public InsertUserProfileResponseTO(String projXTrackingId){
		this.projXTrackingId = projXTrackingId;
	}
	
	public String getProjXTrackingId() {
		return projXTrackingId;
	}
	public boolean isInserted() {
		return inserted;
	}
	public void setInserted(boolean inserted) {
		this.inserted = inserted;
	}

	public String getUserProfileId() {
		return userProfileId;
	}

	public void setUserProfileId(String userProfileId) {
		this.userProfileId = userProfileId;
	}
	
	
}
