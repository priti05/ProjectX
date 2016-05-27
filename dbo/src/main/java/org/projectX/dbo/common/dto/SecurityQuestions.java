package org.projectX.dbo.common.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotBlank;
import org.projectX.dbo.profile.dto.SecurityQuestionAndAnswer;

@Entity
@Table(name="SECURITY_QUESTION")
public class SecurityQuestions {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SECURITY_QUESTION_ID")
	private long securityId;
	
	@NotBlank(message="QUESTION can't be null or empty")
	@Size(max=500,message="QUESTION must be less than {max} character long")
	@Column(name="QUESTION" )
	@Lob
	private String question;

	@OneToMany(mappedBy="securityQuestion")
	@Cascade({CascadeType.ALL})
	private List<SecurityQuestionAndAnswer> securityQuestionAndAnswerList;

	public long getSecurityId() {
		return securityId;
	}

	public void setSecurityId(long securityId) {
		this.securityId = securityId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<SecurityQuestionAndAnswer> getSecurityQuestionAndAnswerList() {
		return securityQuestionAndAnswerList;
	}

	public void setSecurityQuestionAndAnswerList(List<SecurityQuestionAndAnswer> securityQuestionAndAnswerList) {
		this.securityQuestionAndAnswerList = securityQuestionAndAnswerList;
	}
	
	
	
	
}
