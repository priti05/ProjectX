package org.projectX.dbo.customException;

public class ProjectXException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8392750658746841274L;
	
	private String apiName;
	private String projectXTrackingId;
	private String errorCode;
	private String errorDescrition;
	
	public ProjectXException(String apiName, String projectXTrackingId, String errorCode, String errorDescrition)
	{
		super();
		this.apiName = apiName;
		this.projectXTrackingId = projectXTrackingId;
		this.errorCode = errorCode;
		this.errorDescrition = errorDescrition;
		
	}

	public String getApiName() {
		return apiName;
	}

	public String getProjectXTrackingId() {
		return projectXTrackingId;
	}


	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorDescrition() {
		return errorDescrition;
	}	

}
