package org.projectX.dbo.login.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="LAST_LOGGED_IN_HISTORY")
public class LastLoggedInHistory implements Comparable<LastLoggedInHistory> {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="LAST_LOGGED_IN_HISTORY_ID")
	private Long LastLoggedInHistoryId;
	
	@Column(name="LAST_LOGGED_IN")
	@NotNull(message="LAST_LOGGED_IN can't be null")
	@Past(message="LAST_LOGGED_IN date can't be older than current date")
	private Date lastLoggedIn;
	
	@ManyToOne
	@JoinColumn(name="LOGIN_BIOGRAPHY_ID",foreignKey=@ForeignKey(name="LST_LOGGED_HSTRY_FK_LGN_BIO"))
	@NotBlank(message="LOGIN_BIOGRAPHY_ID can't be null in LAST_LOGGED_IN_HISTORY")
	private LoginBiography loginBiography;

	public LastLoggedInHistory(LoginBiography loginBiography){
		this.lastLoggedIn = new Date();
		this.loginBiography = loginBiography;
	}
	
	
	public Long getLastLoggedInHistoryId() {
		return LastLoggedInHistoryId;
	}



	public void setLastLoggedInHistoryId(Long lastLoggedInHistoryId) {
		LastLoggedInHistoryId = lastLoggedInHistoryId;
	}



	public Date getLastLoggedIn() {
		return lastLoggedIn;
	}



	public void setLastLoggedIn(Date lastLoggedIn) {
		this.lastLoggedIn = lastLoggedIn;
	}



	public LoginBiography getLoginBiography() {
		return loginBiography;
	}



	public void setLoginBiography(LoginBiography loginBiography) {
		this.loginBiography = loginBiography;
	}



	public int compareTo(LastLoggedInHistory lastLoggedInHistory) {
		// TODO Auto-generated method stub
		Date previousDate = lastLoggedInHistory.getLastLoggedIn();
		Date currentDate = getLastLoggedIn();
		return previousDate.compareTo(currentDate);
	}

}
