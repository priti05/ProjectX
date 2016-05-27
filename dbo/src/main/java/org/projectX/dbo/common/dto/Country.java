package org.projectX.dbo.common.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="COUNTRY",indexes=@Index(name="COUNTRY_NAME_IDX",columnList="COUNTRY_NAME",unique=true))
public class Country {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="COUNTRY_ID")
	private Integer countryId;
	
	@NotBlank(message="COUNTRY_NAME can't be null or empty")
	@Column(name="COUNTRY_NAME",columnDefinition="varchar(50)")
	private String countryName;
	
	@OneToMany(mappedBy="country")
	@Cascade({CascadeType.ALL})
	private List<State> stateList;

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public List<State> getStateList() {
		return stateList;
	}

	public void setStateList(List<State> stateList) {
		this.stateList = stateList;
	}

	
	
	
	
}
