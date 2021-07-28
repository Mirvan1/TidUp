package com.example.myapplication.Models;

public class EditProfileResponse{
	private Boolean tf;
	private String companyID;
	private String text;
	private String username;

	public void setTf(Boolean tf){
		this.tf = tf;
	}

	public boolean isTf(){
		return tf;
	}

	public void setCompanyID(String companyID){
		this.companyID = companyID;
	}

	public String getCompanyID(){
		return companyID;
	}

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	@Override
 	public String toString(){
		return 
			"EditProfileResponse{" + 
			"tf = '" + tf + '\'' + 
			",companyID = '" + companyID + '\'' + 
			",text = '" + text + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}
