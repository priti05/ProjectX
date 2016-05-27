package org.projectX.dbo.profile.dto;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.projectX.dbo.common.dto.Occupation;
import org.projectX.dbo.enumHelper.SexEnum;
import org.projectX.dbo.login.dto.LoginInformation;
import org.projectX.dbo.profile.classLevelConstraint.ValidValidationMethod;


/**
 * <p>This is hibernate entity class. @{link UserProfile} class is the parent 
 * class of any entity class that holds user information in <em>ProjectX</em>
 * {@link #getPhoneNumber()} and {@link #getEmailAddress()} have two unique 
 * constraint</p>
 *
 * @author  Priti Patel
 * @see     org.projectX.dbo.profile.classLevelConstraint.ValidValidationMethod
 * @see		org.projectX.dbo.common.dto.Occupation
 * @since   0.0.1
 */
@Entity
@Table(name="USER_PROFILE",uniqueConstraints={@UniqueConstraint(columnNames="PHONE_NUMBER",name="PHONE_NUMBER_UQ_USER_PROFILE"),
											  @UniqueConstraint(columnNames="EMAIL_ADDRESS",name="EMAIL_ADDRESS_UQ_USER_PROFILE")})
@ValidValidationMethod(message="Either email or phoneNumber should have valid value")
public class UserProfile {
	
	@Id
	@GeneratedValue( generator = "userProfileIdGenerator")
	@GenericGenerator( name = "userProfileIdGenerator",
    strategy = "org.projectX.dbo.profile.customIdGenerator.CustomUUIDGenerator")
	@Column(name="USER_PROFILE_ID",columnDefinition="varchar(50)")
	private String userProfileId;
	
	
	@NotNull
	@Size.List({
		@Size(min=1,message="FIRST_NAME must be atleast 1 character long"),
		@Size(max=50,message="FIRST_NAME must be less than 50 character long")
	})
	@Column(name="FIRST_NAME",columnDefinition="varchar(60)")
	private String firstName;
	
	@NotNull
	@Size.List({
		@Size(min=1,message="LAST_NAME must be atleast 1 character long"),
		@Size(max=50,message="LAST_NAME must be less than 50 character long")
	})
	@Column(name="LAST_NAME", columnDefinition="varchar(60)")
	private String lastName;
	
	@Size.List({
		@Size(min=1,message="MIDDLE_NAME must be atleast 1 character long"),
		@Size(max=50,message="MIDDLE_NAME must be less than 50 character long")
	})
	@Column(name="MIDDLE_NAME", columnDefinition="varchar(60)")
	private String middleName;
	
	@Size.List({
		@Size(min=1,message="NICK_NAME must be atleast 1 character long"),
		@Size(max=50,message="NICK_NAME must be less than 50 character long")
	})
	@Column(name="NICK_NAME", columnDefinition="varchar(60)")
	private String nickName;
	
	@Size.List({
		@Size(min=1,message="EMPLOYER must be atleast 1 character long"),
		@Size(max=50,message="EMPLOYER must be less than 50 character long")
	})
	@Column(name="EMPLOYER", columnDefinition="varchar(60)")
	private String employer;
	
	@Column(name="PHONE_NUMBER", columnDefinition="varchar(20) default 'NA'")
	private String phoneNumber;
	
	@Column(name="EMAIL_ADDRESS", columnDefinition="varchar(255) default 'NA'")
	private String emailAddress;
	
	@Enumerated(EnumType.STRING)
	@Column(name="SEX", columnDefinition="char(10)")
	private SexEnum sex;
	
	@ManyToOne
	@JoinColumn(name="OCCUPATION_ID",foreignKey=@ForeignKey(name="USER_PROFILE_FK_OCCUPATION_ID"))
	private Occupation occupation;
	
	@OneToOne(mappedBy="userProfile")
	@Cascade({CascadeType.ALL})
	private LoginInformation LoginInformation;
	
	@OneToMany(mappedBy="userProfile")
	@Cascade({CascadeType.ALL})
	private List<Address> addressList;
	
	@OneToMany(mappedBy="userProfile")
	@Cascade({CascadeType.ALL})
	private List<SecurityQuestionAndAnswer> securityQuestionAndAnswerList;
	
	@OneToOne(mappedBy="userProfile")
	@Cascade({CascadeType.ALL})
	private VerificationMethod verificationMethod;
	
	@OneToOne(mappedBy="userProfile")
	@Cascade({CascadeType.ALL})
	private UserProfilePicture userProfilePicture;
	
	@Column(name="REGISTRATION_DATE")
	@NotNull(message="REGISTRATION_DATE can't be null")
	@Past(message="REGISTRATION_DATE date can't be older than current date")
	private Date registrationDate;
	
	@Column(name="LAST_PROFILE_UPDATED_DATE")
	@NotNull(message="LAST_PROFILE_UPDATED_DATE can't be null")
	@Past(message="LAST_PROFILE_UPDATED_DATE date can't be older than current date")
	private Date lastProfileUpdatedDate;
	
	public UserProfile(String firstName, String lastName){
		this.firstName = firstName;
		this.lastName = lastName;
		this.registrationDate = new Date();
		this.lastProfileUpdatedDate = new Date();
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
     * <p>This method act as primary key generator for UserProfile Entity.
     * Since It uses {@link ShardedUUIDGenerator} implementation, It returns 
     * {@link BigInteger} value. </p>
     * @since  0.0.1
     * @return BigInteger
     * 
     */
	public String getUserProfileId() {
		return userProfileId;
	}

	/**
	 * @since 0.0.1
	 * @param BigInteger
	 */
	public void setUserProfileId(String userProfileId) {
		this.userProfileId = userProfileId;
	}

	/**
     * <p>first name Length must be between 1 to 50. Null value aren't allowed</p>
     * @since  0.0.1
     * @return String
     * 
     */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @since 0.0.1
	 * @param String
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
     * <p>last name Length must be between 1 to 50. Null value aren't allowed</p>
     * @since  0.0.1
     * @return String
     * 
     */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @since 0.0.1
	 * @param String
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
     * <p>Middle name Length must be between 1 to 50 if not null.</p>
     * @since  0.0.1
     * @return String
     * 
     */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @since 0.0.1
	 * @param String
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
     * <p>Nick name Length must be between 1 to 50 if not null.</p>
     * @since  0.0.1
     * @return String
     * 
     */
	public String getNickName() {
		return nickName;
	}

	/**
	 * @since 0.0.1
	 * @param String
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
     * <p>Employer Length must be between 1 to 50 if not null.</p>
     * @since  0.0.1
     * @return String
     * 
     */
	public String getEmployer() {
		return employer;
	}

	/**
	 * @since 0.0.1
	 * @param String
	 */
	public void setEmployer(String employer) {
		this.employer = employer;
	}

	/**
	 * <p>Value must be either {@link SexEnum#MALE} or {@link SexEnum#FEMALE}.
	 *  Null or empty value are not allowed.</p>
	 * @since  0.0.1
	 * @return SexEnum
	 */
	public SexEnum getSex() {
		return sex;
	}

	/**
	 * @since 0.0.1
	 * @param SexEnum
	 */
	public void setSex(SexEnum sex) {
		this.sex = sex;
	}

	/**
	 * <p>This is a FK between {@link Occupation} and {@link UserProfile} Entities.
	 * It defines <em>ManyToOne</em> relation As userProfile is a child of
	 * Occupation entity</p>
	 * @since  0.0.1
	 * @return Occupation
	 */
	public Occupation getOccupation() {
		return occupation;
	}

	/**
	 * @since 0.0.1
	 * @param Occupation
	 */
	public void setOccupation(Occupation occupation) {
		this.occupation = occupation;
	}
	
	/**
	 * <p>This is a FK between {@link UserProfile} and {@link Address} Entities.
	 * It defines <em>OneToMany</em> relation As UserProfile is a Parent of
	 * Address entity. Cascading is been set to ALL CascadeType</p>
	 * @since  0.0.1
	 * @return List<Address>
	 */
	public List<Address> getAddressList() {
		return addressList;
	}

	/**
	 * @since  0.0.1
	 * @param List<Address>
	 */
	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}

	/**
	 * <p>This is a FK between {@link UserProfile} and {@link SecurityQuestionAndAnswer} Entities.
	 * It defines <em>OneToMany</em> relation As UserProfile is a Parent of
	 * SecurityQuestionAndAnswer entity. Cascading is been set to ALL CascadeType</p>
	 * @since  0.0.1
	 * @return List<SecurityQuestionAndAnswer>
	 */
	public List<SecurityQuestionAndAnswer> getSecurityQuestionAndAnswerList() {
		return securityQuestionAndAnswerList;
	}

	/**
	 * @since  0.0.1
	 * @param List<SecurityQuestionAndAnswer>
	 */
	public void setSecurityQuestionAndAnswerList(List<SecurityQuestionAndAnswer> securityQuestionAndAnswerList) {
		this.securityQuestionAndAnswerList = securityQuestionAndAnswerList;
	}

	/**
	 * <p>This is a FK between {@link UserProfile} and {@link VerificationMethod} Entities.
	 * It defines <em>OneToOne</em> relation. UserProfile is a Parent of
	 * VerificationMethod entity. Cascading is been set to ALL CascadeType</p>
	 * @since  0.0.1
	 * @return VerificationMethod
	 */
	public VerificationMethod getVerificationMethod() {
		return verificationMethod;
	}

	/**
	 * @since  0.0.1
	 * @param VerificationMethod
	 */
	public void setVerificationMethod(VerificationMethod verificationMethod) {
		this.verificationMethod = verificationMethod;
	}
	
	/**
	 * <p>Date and Time when user registered</p>
	 * @since  0.0.1
	 * @return Date
	 */
	public Date getRegistrationDate() {
		return registrationDate;
	}

	/**
	 * @since  0.0.1
	 * @param Date
	 */
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	/**
	 * <p>Date and Time when user last updated profile</p>
	 * @since  0.0.1
	 * @return Date
	 */
	public Date getLastProfileUpdatedDate() {
		return lastProfileUpdatedDate;
	}

	/**
	 * @since  0.0.1
	 * @param Date
	 */
	public void setLastProfileUpdatedDate(Date lastProfileUpdatedDate) {
		this.lastProfileUpdatedDate = lastProfileUpdatedDate;
	}

	/**
	 * <p>This is a FK between {@link UserProfile} and {@link LoginInformation} Entities.
	 * It defines <em>OneToOne</em> relation. UserProfile is a Parent of
	 * LoginInformation entity. Cascading is been set to ALL CascadeType</p>
	 * @since  0.0.1
	 * @return LoginInformation
	 */
	public LoginInformation getLoginInformation() {
		return LoginInformation;
	}
	
	/**
	 * @since  0.0.1
	 * @param LoginInformation
	 */
	public void setLoginInformation(LoginInformation loginInformation) {
		LoginInformation = loginInformation;
	}

	/**
	 * <p>This is a FK between {@link UserProfile} and {@link UserProfilePicture} Entities.
	 * It defines <em>OneToOne</em> relation. UserProfile is a Parent of
	 * UserProfilePicture entity. Cascading is been set to ALL CascadeType</p>
	 * @since  0.0.1
	 * @return UserProfilePicture
	 */
	public UserProfilePicture getUserProfilePicture() {
		return userProfilePicture;
	}

	/**
	 * @since  0.0.1
	 * @param UserProfilePicture
	 */
	public void setUserProfilePicture(UserProfilePicture userProfilePicture) {
		this.userProfilePicture = userProfilePicture;
	}
	
	

}
