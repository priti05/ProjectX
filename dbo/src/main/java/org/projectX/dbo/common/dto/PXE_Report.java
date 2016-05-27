package org.projectX.dbo.common.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="PXE_REPORT")
public class PXE_Report  implements Comparable<PXE_Report>{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PXE_REPORT_ID")
	private long PXE_Report_Id;
	
	@ManyToOne
	@JoinColumn(name="ERROR_CODE", foreignKey=@ForeignKey(name="PXE_FK_PXE_REPORT"))
	@NotNull(message="ERROR_CODE can't be null in PXE_REPORT")
	private PXE pxe;
	
	@Column(name="REPORTED_ON")
	@NotNull(message="REPORTED_ON can't be null in PXE_REPORT")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date reportedOn;
	
	@OneToOne
	@JoinColumn(name="PROJECT_X_TRACKING_ID",foreignKey=@ForeignKey(name="PROJ_X_TRCK_FK_PXE_RPRT"))
	@NotNull(message="PROJECT_X_TRACKING_ID can't be null in PXE_REPORT")
	private ProjectXTrackingIdGeneratorTracker projectXTrackingIdGeneratorTracker;

	public long getPXE_Report_Id() {
		return PXE_Report_Id;
	}

	public void setPXE_Report_Id(long pXE_Report_Id) {
		PXE_Report_Id = pXE_Report_Id;
	}

	public PXE getPxe() {
		return pxe;
	}

	public void setPxe(PXE pxe) {
		this.pxe = pxe;
	}

	public Date getReportedOn() {
		return reportedOn;
	}

	public void setReportedOn(Date reportedOn) {
		this.reportedOn = reportedOn;
	}

	public ProjectXTrackingIdGeneratorTracker getProjectXTrackingIdGeneratorTracker() {
		return projectXTrackingIdGeneratorTracker;
	}

	public void setProjectXTrackingIdGeneratorTracker(
			ProjectXTrackingIdGeneratorTracker projectXTrackingIdGeneratorTracker) {
		this.projectXTrackingIdGeneratorTracker = projectXTrackingIdGeneratorTracker;
	}
	
	public int compareTo(PXE_Report pxe_Report) {
		// TODO Auto-generated method stub
		Date previousDate = pxe_Report.getReportedOn();
		Date currentDate = getReportedOn();
		return previousDate.compareTo(currentDate);
	}
	

}
