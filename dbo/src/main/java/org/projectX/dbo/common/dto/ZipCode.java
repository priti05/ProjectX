package org.projectX.dbo.common.dto;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotBlank;
import org.projectX.dbo.profile.dto.Address;

@Entity
@Table(name="ZIP_CODE",indexes=@Index(name="ZIP_CODE_IDX",columnList="ZIPCODE",unique=true))
public class ZipCode {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ZIPCODE_ID")
	private Long zipCodeId;
	
	@NotBlank(message="ZIPCODE can't be null or empty")
	@Column(name="ZIPCODE",columnDefinition="varchar(15)")
	private String zipCode;
	
	@ManyToOne
	@JoinColumn(name="TOWN_ID",  foreignKey=@ForeignKey(name="ZIP_CODE_FK_TOWN"))
	@NotBlank(message="TOWN_ID can't be null or empty in ZIP_CODE")
	private Town town;
	
	@OneToOne(mappedBy="zipCode")
	@Cascade({CascadeType.ALL})
	private Address address;

	public Long getZipCodeId() {
		return zipCodeId;
	}

	public void setZipCodeId(Long zipCodeId) {
		this.zipCodeId = zipCodeId;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Town getTown() {
		return town;
	}

	public void setTown(Town town) {
		this.town = town;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	
	

}
