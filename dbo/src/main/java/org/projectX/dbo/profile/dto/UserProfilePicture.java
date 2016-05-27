package org.projectX.dbo.profile.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="USER_PROFILE_PICTURE")
public class UserProfilePicture {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USER_PROFILE_PICTURE_ID")
	private Long UserProfilePicture;
	
	@Column(name="PICTURE")
	@Lob
	private Byte[] picture;
	
	@OneToOne
	@JoinColumn(name="USER_PROFILE_ID",foreignKey=@ForeignKey(name="USR_PROF_FK_USR_PROF_PIC"))
	private UserProfile userProfile;

	public Long getUserProfilePicture() {
		return UserProfilePicture;
	}

	public void setUserProfilePicture(Long userProfilePicture) {
		UserProfilePicture = userProfilePicture;
	}

	public Byte[] getPicture() {
		return picture;
	}

	public void setPicture(Byte[] picture) {
		this.picture = picture;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}
	
	
}
