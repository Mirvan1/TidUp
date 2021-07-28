package com.example.myapplication.Models;

public class UserProfile{
	private String password;
	private String companyID;
	private Boolean tf;
	private String role;
	private String companyName;
	private String text;
	private String email;
	private String username;

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

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

	public void setRole(String role){
		this.role = role;
	}

	public String getRole(){
		return role;
	}

	public void setCompanyName(String companyName){
		this.companyName = companyName;
	}

	public String getCompanyName(){
		return companyName;
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

	@Override
 	public String toString(){
		return 
			"UserProfile{" + 
			"password = '" + password + '\'' + 
			",companyID = '" + companyID + '\'' + 
			",tf = '" + tf + '\'' + 
			",role = '" + role + '\'' + 
			",companyName = '" + companyName + '\'' + 
			",text = '" + text + '\'' + 
			",email = '" + email + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}
