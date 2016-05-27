package org.projectX.dbo.profile.dto;


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
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.projectX.dbo.common.dto.ZipCode;

@Entity
@Table(name="ADDRESS_BOOK")
public class Address {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ADDRESS_BOOK_ID")
	private Long addressId;
	
	@NotBlank(message="HOUSE_NUMBER can't be null or empty")
	@Size.List ({
	    @Size(max=40, message="HOUSE_NUMBER must be less than {max} characters")
	})
	@Column(name="HOUSE_NUMBER", columnDefinition="varchar(40)")
	private String houseNumber;
	
	@Size.List ({
		@Size(min=1, message="HOUSE_NUMBER must be at least {min} characters"),
	    @Size(max=40, message="HOUSE_NUMBER must be less than {max} characters")
	})
	@Column(name="APT_NUMBER", columnDefinition="varchar(40)")
	private String aptNumber;
	
	@NotBlank(message="STREET_NAME can't be null or empty")
	@Size.List ({
	    @Size(max=250, message="STREET_NAME must be less than {max} characters")
	})
	@Column(name="STREET_NAME", columnDefinition="varchar(250)")
	private String streetName;
	
	@Size.List ({
	    @Size(max=250, message="STREET_NAME_2 must be less than {max} characters")
	})
	@Column(name="STREET_NAME_2", columnDefinition="varchar(250)")
	private String streetName2;
	
	@OneToOne
	@JoinColumn(name="ZIPCODE_ID" ,foreignKey=@ForeignKey(name="ADDRESS_BOOK_FK_ZIPCODE"))
	@NotBlank(message="ZIPCODE_ID can't be null or empty in ADDRESS_BOOK")
	private ZipCode zipCode;
	
	@ManyToOne
	@JoinColumn(name="USER_PROFILE_ID",foreignKey=@ForeignKey(name="USER_PROFILE_FK_ZIPCODE"))
	@NotBlank(message="USER_PROFILE_ID can't be null or empty in userProfile")
	private UserProfile userProfile;

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getAptNumber() {
		return aptNumber;
	}

	public void setAptNumber(String aptNumber) {
		this.aptNumber = aptNumber;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getStreetName2() {
		return streetName2;
	}

	public void setStreetName2(String streetName2) {
		this.streetName2 = streetName2;
	}

	public ZipCode getZipCode() {
		return zipCode;
	}

	public void setZipCode(ZipCode zipCode) {
		this.zipCode = zipCode;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}
	
	
	
	

}
