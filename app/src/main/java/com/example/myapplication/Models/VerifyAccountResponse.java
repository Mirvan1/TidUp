package com.example.myapplication.Models;

public class VerifyAccountResponse{
	private String companyID;
	private Boolean tf;
	private String text;
	private String email;
	private String username;
	private String verificationCode;
	private Integer status;

	public void setCompanyID(String companyID){
		this.companyID = companyID;
	}

	public String getCompanyID(){
		return companyID;
	}

	public void setTf(Boolean tf){
		this.tf = tf;
	}

	public boolean isTf(){
		return tf;
	}

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	public void setVerificationCode(String verificationCode){
		this.verificationCode = verificationCode;
	}

	public String getVerificationCode(){
		return verificationCode;
	}

	public void setStatus(Integer status){
		this.status = status;
	}

	public Integer getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"VerifyAccountResponse{" + 
			"companyID = '" + companyID + '\'' + 
			",tf = '" + tf + '\'' + 
			",text = '" + text + '\'' + 
			",email = '" + email + '\'' + 
			",username = '" + username + '\'' + 
			",verificationCode = '" + verificationCode + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
