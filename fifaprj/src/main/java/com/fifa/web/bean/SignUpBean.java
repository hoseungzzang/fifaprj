package com.fifa.web.bean;




import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

public class SignUpBean {
	
	@Length(min=5, max=20, message="���̵�� 5~20���ڸ� �����մϴ�.")
	private String userId;
	
	@Length(min=2, max=5, message="�̸��� 2~5���ڸ� �����մϴ�." )
	private String userName;
	
	@Length(min=5, max=20, message="��й�ȣ�� 5~20���ڸ� �����մϴ�.")
	private String userPassword;
	
	@Range(min=0,max=100, message="�÷��̾���� �����ϼ���.")
	private int userPindex;
	
	private char userYn;
	
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
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public int getUserPindex() {
		return userPindex;
	}
	public void setUserPindex(int userPindex) {
		this.userPindex = userPindex;
	}
	public char getUserYn() {
		return userYn;
	}
	public void setUserYn(char userYn) {
		this.userYn = userYn;
	}
	
	@Override
	public String toString() {
	return "SignUpBean [userid=" + userId + ", passwd=" + userPassword + ", name=" + userName + ", userPindex=" + userPindex +
			", userYn=" +userYn +"]";
	 }
}
