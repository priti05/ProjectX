package org.projectX.dbo.login.dto;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;
import org.projectX.dbo.profile.dto.UserProfile;
import org.projectX.dbo.utils.UtilsConstants;

@Entity
@Table(name="LOGIN_INFORMATION",
			indexes={ @Index(name="UNAME_IDX_LGNINFO", columnList="USER_NAME", unique=true) })
public class LoginInformation {
	
	@Id
	@GenericGenerator(name="user_id", strategy="org.projectX.dbo.login.customIdGenerator.UserIdGenerator")
	@GeneratedValue(generator="user_id")
	@Column(name="USER_ID")
	private String userId;
	
	@Column(name="USER_NAME", columnDefinition="varchar(50)")
	@Size.List ({
	    @Size(min=8, message="userName must be at least {min} characters"),
	    @Size(max=40, message="userName must be less than {max} characters")
	})
	@Pattern(regexp = UtilsConstants.userName, message = "userName is not in valid format. only alpha letters, numbers and _  are allowed")
	@NotBlank(message="userName can't be null")
	private String userName;
	
	@Column(name="PASSWORD")
	@NotBlank(message="password can't be null")
	@Lob
	private String password;
	
	@OneToOne(mappedBy="loginInformation")
	@Cascade({CascadeType.ALL})
	private LoginBiography loginBiography;
	
	@OneToOne
	@JoinColumn(name="USER_PROFILE_ID", foreignKey = @ForeignKey(name = "USR_PROF_FK_LGN_INFO"))
	@NotBlank(message="USER_PROFILE_ID can't be null or empty in LOGIN_INFORMATION")
	private UserProfile userProfile;
	

	public LoginInformation(UserProfile userProfile, String userName, String password){
		this.userProfile = userProfile;
		this.userName = userName;
		this.password = password;		
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LoginBiography getLoginBiography() {
		return loginBiography;
	}

	public void setLoginBiography(LoginBiography loginBiography) {
		this.loginBiography = loginBiography;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}
	
	
	

}
