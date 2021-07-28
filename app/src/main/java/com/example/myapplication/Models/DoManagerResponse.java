package com.example.myapplication.Models;

public class DoManagerResponse{
	private String companyID;
	private Boolean tf;
	private String text;
	private String userID;

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

	public void setUserID(String userID){
		this.userID = userID;
	}

	public String getUserID(){
		return userID;
	}

	@Override
 	public String toString(){
		return 
			"DoManagerResponse{" + 
			"companyID = '" + companyID + '\'' + 
			",tf = '" + tf + '\'' + 
			",text = '" + text + '\'' + 
			",userID = '" + userID + '\'' + 
			"}";
		}
}
