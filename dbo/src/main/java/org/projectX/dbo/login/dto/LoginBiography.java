package org.projectX.dbo.login.dto;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotBlank;
import org.projectX.dbo.enumHelper.YesOrNoEnum;

@Entity
@Table(name="LOGIN_BIOGRAPHY")
public class LoginBiography {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="LOGIN_BIOGRAPHY_ID")
	private Long loginBiographyId;
	
	@Column(name="LOGIN_ATTEMPTS",  columnDefinition="int default 0")
	@NotNull (message="Entry for LOGIN_ATTEMPTS can't be null")
	private int loginAttemps;
	
	@Enumerated(EnumType.STRING)
	@Column(name="TEMP_PASSWORD", columnDefinition="char(3) default 'NO'")
	@NotNull (message="TEMP_PASSWORD can't be null")
	private YesOrNoEnum hasTempPassword;
	
	@Enumerated(EnumType.STRING)
	@Column(name="SOFT_LOCK", columnDefinition="char(3) default 'NO'")
	@NotNull (message="SOFT_LOCK can't be null")
	private YesOrNoEnum softLock;
	
	@Enumerated(EnumType.STRING)
	@Column(name="HARD_LOCK", columnDefinition="char(3) default 'NO'")
	@NotNull (message="HARD_LOCK can't be null")
	private YesOrNoEnum hardLock;
	
	@Enumerated(EnumType.STRING)
	@Column(name="DISABLES", columnDefinition="char(3) default 'NO'")
	@NotNull (message="HARD_LOCK can't be null")
	private YesOrNoEnum disables;
	
	@OneToOne
	@JoinColumn(name="USER_ID", foreignKey=@ForeignKey(name="LGN_BIO_FK_LIN_INFO"))
	@NotBlank(message="USER_ID can't be null in LOGIN_BIOGRAPHY")
	private LoginInformation loginInformation;
	
	
	public LoginBiography(LoginInformation loginInformation){
		this.loginInformation = loginInformation;
		
	}
	
	@OneToMany(mappedBy="loginBiography")
	@Cascade({CascadeType.ALL})
	private Set<LastLoggedInHistory> lastLoggedInHistoryList;

	public Long getLoginBiographyId() {
		return loginBiographyId;
	}

	public void setLoginBiographyId(Long loginBiographyId) {
		this.loginBiographyId = loginBiographyId;
	}

	public int getLoginAttemps() {
		return loginAttemps;
	}

	public void setLoginAttemps(int loginAttemps) {
		this.loginAttemps = loginAttemps;
	}

	public YesOrNoEnum getHasTempPassword() {
		return hasTempPassword;
	}

	public void setHasTempPassword(YesOrNoEnum hasTempPassword) {
		this.hasTempPassword = hasTempPassword;
	}

	public YesOrNoEnum getSoftLock() {
		return softLock;
	}

	public void setSoftLock(YesOrNoEnum softLock) {
		this.softLock = softLock;
	}

	public YesOrNoEnum getHardLock() {
		return hardLock;
	}

	public void setHardLock(YesOrNoEnum hardLock) {
		this.hardLock = hardLock;
	}

	public LoginInformation getLoginInformation() {
		return loginInformation;
	}

	public void setLoginInformation(LoginInformation loginInformation) {
		this.loginInformation = loginInformation;
	}

	public Set<LastLoggedInHistory> getLastLoggedInHistoryList() {
		return lastLoggedInHistoryList;
	}

	public void setLastLoggedInHistoryList(Set<LastLoggedInHistory> lastLoggedInHistoryList) {
		this.lastLoggedInHistoryList = lastLoggedInHistoryList;
	}


	public YesOrNoEnum getDisables() {
		return disables;
	}

	public void setDisables(YesOrNoEnum disables) {
		this.disables = disables;
	}
	
	
	
	
}
