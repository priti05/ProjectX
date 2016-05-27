package org.projectX.dbo.common.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.projectX.dbo.profile.dto.UserProfile;

@Entity
@Table(name="OCCUPATION",uniqueConstraints=@UniqueConstraint(columnNames="OCCUPATION_TYPE",name="OCCUPATION_TYPE_UQ"))
public class Occupation {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="OCCUPATION_ID")
	private Integer occupationId;
	
	@NotNull
	@Size.List({
		@Size(min=2,message="OCCUPATION_TYPE must be atleast 2 character long")
	})
	@Column(name="OCCUPATION_TYPE", columnDefinition="varchar(150)")
	private String occupationType;
	
	@OneToMany(mappedBy="occupation")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.PERSIST})
	private List<UserProfile> userProfileList;

	public Integer getOccupationId() {
		return occupationId;
	}

	public void setOccupationId(Integer occupationId) {
		this.occupationId = occupationId;
	}

	public String getOccupationType() {
		return occupationType;
	}

	public void setOccupationType(String occupationType) {
		this.occupationType = occupationType;
	}

	public List<UserProfile> getUserProfileList() {
		return userProfileList;
	}

	public void setUserProfileList(List<UserProfile> userProfileList) {
		this.userProfileList = userProfileList;
	}
	
	
	
	
	
	
	
	
}
