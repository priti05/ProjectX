package org.projectX.dbo.profile.dao.request;

import org.projectX.dbo.enumHelper.SexEnum;

/**
 * <p>This is a request class to perform Create operation on <em>User Profile</em></p>
 * @author Priti Patel
 * @since  0.0.1
 * @see org.projectX.dbo.profile.dao.ProfileDAOImplementations
 */
public class InsertUserProfileRequestTO {

	
	private String firstName;
	private String lastName;
	private String middleName;
	private String nickName;
	private String employer;
	private String phoneNumber;
	private String email;
	private SexEnum sex;
	private Integer occupationId;
	
	public InsertUserProfileRequestTO(String firstName,String lastName, String phoneNumber, String email){
		this.firstName = firstName;
		this.lastName =lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getEmployer() {
		return employer;
	}
	public void setEmployer(String employer) {
		this.employer = employer;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public String getEmail() {
		return email;
	}
	 
	public SexEnum getSex() {
		return sex;
	}
	public void setSex(SexEnum sex) {
		this.sex = sex;
	}
	public Integer getOccupationId() {
		return occupationId;
	}
	public void setOccupationId(Integer occupationId) {
		this.occupationId = occupationId;
	}
	
	
	
}
