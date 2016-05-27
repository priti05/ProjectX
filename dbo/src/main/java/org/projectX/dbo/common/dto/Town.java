package org.projectX.dbo.common.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="TOWN")
public class Town {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="TOWN_ID")
	private Long town_Id;
	
	@NotBlank(message="TOWN_NAME can't be null or empty")
	@Column(name="TOWN_NAME", columnDefinition="varchar(50)")
	private String townName;
	
	@OneToMany(mappedBy="town")
	@Cascade({CascadeType.ALL})
	private List<ZipCode> zipCodeList;
	
	@ManyToOne
	@JoinColumn(name="STATE_ID" , foreignKey=@ForeignKey(name="STATE_FK_TOWN"))
	@NotBlank(message="STATE_ID can't be null or empty in TOWN")
	private State state;

	public Long getTown_Id() {
		return town_Id;
	}

	public void setTown_Id(Long town_Id) {
		this.town_Id = town_Id;
	}

	public String getTownName() {
		return townName;
	}

	public void setTownName(String townName) {
		this.townName = townName;
	}

	public List<ZipCode> getZipCodeList() {
		return zipCodeList;
	}

	public void setZipCodeList(List<ZipCode> zipCodeList) {
		this.zipCodeList = zipCodeList;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	
	
}
