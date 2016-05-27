package org.projectX.dbo.profile.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.projectX.dbo.enumHelper.RegistrationVerificationTypeEnum;
import org.projectX.dbo.enumHelper.RegistrationVerifiedEnum;
import org.projectX.dbo.enumHelper.YesOrNoEnum;
import org.projectX.dbo.utils.UtilsConstants;

@Entity
@Table(name="VERIFICATION_METHOD" , uniqueConstraints=@UniqueConstraint(columnNames={"USER_PROFILE_ID","AGRN"},name="USER_PROFILE_ID_AGRN_UQ"))
public class VerificationMethod {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="VERIFICATION_METHOD_ID")
	private Long verificationMethodId;
	
	@Enumerated(EnumType.STRING)
	@Column(name="REGISTRATION_VERIFICATION_TYPE" , columnDefinition="varchar(10)")
	@NotBlank(message="REGISTRATION_VERIFICATION_TYPE can't be null or empty")
	private RegistrationVerificationTypeEnum registrationVerificationType;
	
	@NotBlank(message="AGRN can't be null or empty")
	@Column(name="AGRN", columnDefinition="varchar(10)")
	@Pattern(regexp = UtilsConstants.agrnPattern, message = "agrn is not in valid format. only eight digits are allowed")
	private String agrn; //Automatic Generated Registration Number
	
	@Enumerated(EnumType.STRING)
	@Column(name="REGISTRATION_VERIFIED", columnDefinition="varchar(20) default 'NOT_VERIFIED'")
	private RegistrationVerifiedEnum registrationVerified;
	
	@Column(name="NUMBER_OF_AGRN",columnDefinition="int default 0")
	private int number_of_agrn;
	
	@Enumerated(EnumType.STRING)
	@Column(name="REACHED_MAX_NUMBER_OF_AGRN", columnDefinition="char(5) default 'NO'")
	private YesOrNoEnum reachedMaxNumberOfagrn;
	
	@OneToOne
	@JoinColumn(name="USER_PROFILE_ID",foreignKey=@ForeignKey(name="USR_PROF_FK_VERI_MTHD"))
	@NotBlank(message="USER_PROFILE_ID can't be null or empty in VERIFICATION_METHOD")
	private UserProfile userProfile;
	
	public VerificationMethod(RegistrationVerificationTypeEnum registrationVerificationType, String agrn,UserProfile userProfile){
		this.registrationVerificationType = registrationVerificationType;
		this.agrn = agrn;
		this.number_of_agrn = 1;
		this.userProfile = userProfile;
	}

	public Long getVerificationMethodId() {
		return verificationMethodId;
	}

	public void setVerificationMethodId(Long verificationMethodId) {
		this.verificationMethodId = verificationMethodId;
	}

	public RegistrationVerificationTypeEnum getRegistrationVerificationType() {
		return registrationVerificationType;
	}

	public void setRegistrationVerificationType(RegistrationVerificationTypeEnum registrationVerificationType) {
		this.registrationVerificationType = registrationVerificationType;
	}

	public String getAgrn() {
		return agrn;
	}

	public void setAgrn(String agrn) {
		this.agrn = agrn;
	}

	public RegistrationVerifiedEnum getRegistrationVerified() {
		return registrationVerified;
	}

	public void setRegistrationVerified(RegistrationVerifiedEnum registrationVerified) {
		this.registrationVerified = registrationVerified;
	}

	public int getNumber_of_agrn() {
		return number_of_agrn;
	}

	public void setNumber_of_agrn(int number_of_agrn) {
		this.number_of_agrn = number_of_agrn;
	}

	public YesOrNoEnum getReachedMaxNumberOfagrn() {
		return reachedMaxNumberOfagrn;
	}

	public void setReachedMaxNumberOfagrn(YesOrNoEnum reachedMaxNumberOfagrn) {
		this.reachedMaxNumberOfagrn = reachedMaxNumberOfagrn;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}
	
	
	

}
