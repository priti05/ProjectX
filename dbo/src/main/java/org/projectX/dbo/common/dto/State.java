package org.projectX.dbo.common.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="STATE")
public class State {
	
	@Id
	@Column(name="STATE_ID", columnDefinition="varchar(6)")
	private String stateId;
	
	@NotBlank(message="STATE_NAME can't be null or empty")
	@Column(name="STATE_NAME", columnDefinition="varchar(50)")
	private String stateName; 
	
	@OneToMany(mappedBy="state")
	@Cascade({CascadeType.ALL})
	private List<Town> townList;
	
	@ManyToOne
	@JoinColumn(name="COUNTRY_ID", foreignKey=@ForeignKey(name="COUNTRY_FK_STATE"))
	@NotBlank(message="COUNTRY_ID can't be null or empty in STATE")
	private Country country;

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public List<Town> getTownList() {
		return townList;
	}

	public void setTownList(List<Town> townList) {
		this.townList = townList;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	
	
	
	
	
}
