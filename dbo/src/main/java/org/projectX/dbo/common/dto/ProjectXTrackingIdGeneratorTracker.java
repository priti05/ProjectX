package org.projectX.dbo.common.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="PROJECT_X_TRACKING_ID_TRACKER")
public class ProjectXTrackingIdGeneratorTracker implements Comparable<ProjectXTrackingIdGeneratorTracker> {
	
	@Id
	@GenericGenerator(name="tracking_id", strategy="org.projectX.dbo.common.customIdGenerator.ProjectXTrackingIdGenerator")
	@GeneratedValue(generator="tracking_id")
	@Column(name="PROJECT_X_TRACKING_ID", columnDefinition="varchar(300)")
	private String trackingId;
	
	@Lob
	@Column(name="REQUEST")
	@NotNull(message="REQUEST can't be null in PROJECT_X_TRACKING_ID_TRACKER")
	private String request;
	
	@Lob
	@Column(name="RESPONSE")
	private String response;
	
	@Column(name="REQUEST_DATE")
	@NotNull(message="REQUEST_DATE can't be null in PROJECT_X_TRACKING_ID_TRACKER")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date requestDate;
	
	@Column(name="API_NAME")
	@NotNull(message="API_NAME can't be null in PROJECT_X_TRACKING_ID_TRACKER")
	private String apiName;
	
	@OneToOne(mappedBy="projectXTrackingIdGeneratorTracker")
	@Cascade({CascadeType.ALL})
	private PXE_Report pxe_report;

	public String getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	
	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	public PXE_Report getPxe_report() {
		return pxe_report;
	}

	public void setPxe_report(PXE_Report pxe_report) {
		this.pxe_report = pxe_report;
	}

	public int compareTo(ProjectXTrackingIdGeneratorTracker projectXTrackingIdGeneratorTracker) {
		// TODO Auto-generated method stub
		Date reqDate = projectXTrackingIdGeneratorTracker.getRequestDate();
		Date currentReqDate = getRequestDate();
		return reqDate.compareTo(currentReqDate);
	}
	
}
