package org.projectX.dbo.profile.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.projectX.dbo.common.dto.SecurityQuestions;

@Entity
@Table(name="SECURITY_QUESTION_ANSWER")
public class SecurityQuestionAndAnswer {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SECURITY_QUESTION_ANSWER_ID")
	private Long SecurityQuestionAndAnswerId;

	@ManyToOne
	@JoinColumn(name="SECURITY_QUESTION_ID", foreignKey=@ForeignKey(name="SEC_QUE_FK_SEC_QUE_ANS"))
	@NotBlank(message="SECURITY_QUESTION_ID can't be null or empty in SECURITY_QUESTION_ANSWER")
	private SecurityQuestions securityQuestion;
	
	
	@NotBlank(message="ANSWER can't be null or empty")
	@Size(max=100,message="ANSWER must be less than {max} character long")
	@Column(name="ANSWER", columnDefinition="varchar(150)")
	private String answer;
	
	@ManyToOne
	@JoinColumn(name="USER_PROFILE_ID",foreignKey=@ForeignKey(name="USR_PROF_FK_SEC_QUE_ANS"))
	@NotBlank(message="USER_PROFILE_ID can't be null in SECURITY_QUESTION_ANSWER")
	private UserProfile userProfile;

	public Long getSecurityQuestionAndAnswerId() {
		return SecurityQuestionAndAnswerId;
	}

	public void setSecurityQuestionAndAnswerId(Long securityQuestionAndAnswerId) {
		SecurityQuestionAndAnswerId = securityQuestionAndAnswerId;
	}

	public SecurityQuestions getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(SecurityQuestions securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}
	
	
	
	
}
