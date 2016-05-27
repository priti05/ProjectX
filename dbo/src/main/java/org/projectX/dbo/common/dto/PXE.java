package org.projectX.dbo.common.dto;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="PXE")
public class PXE{
	
	@Id
	@Column(name="ERROR_CODE", columnDefinition="varchar(100)")
	private String errorCode;
	
	@Column(name="ERROR_DESCRIPTION")
	@Lob
	@NotBlank(message="ERROR_DESCRIPTION in PXE can't be null or empty")
	private String errorDecription;
	
	@Column(name="MODULE", columnDefinition="varchar(100)")
	@NotBlank(message="MODULE cant be null or empty in PXE")
	private String module;

	@OneToMany(mappedBy="pxe")
	@Cascade({CascadeType.ALL})
	private Set<PXE_Report> PXE_ReportList;
	
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDecription() {
		return errorDecription;
	}

	public void setErrorDecription(String errorDecription) {
		this.errorDecription = errorDecription;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}
	
	public Set<PXE_Report> getPXE_ReportList() {
		return PXE_ReportList;
	}

	public void setPXE_ReportList(Set<PXE_Report> pXE_ReportList) {
		PXE_ReportList = pXE_ReportList;
	}

	

}
