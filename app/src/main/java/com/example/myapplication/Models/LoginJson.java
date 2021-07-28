package com.example.myapplication.Models;

public class LoginJson{
	private String password;
	private Boolean tf;
	private String companyID;
	private String role;
	private String mail;
	private String companyName;
	private String id;
	private String text;
	private String username;

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

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

	public void setRole(String role){
		this.role = role;
	}

	public String getRole(){
		return role;
	}

	public void setMail(String mail){
		this.mail = mail;
	}

	public String getMail(){
		return mail;
	}

	public void setCompanyName(String companyName){
		this.companyName = companyName;
	}

	public String getCompanyName(){
		return companyName;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
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
			"{" +
			"password = '" + password + '\'' + 
			",tf = '" + tf + '\'' + 
			",companyID = '" + companyID + '\'' + 
			",role = '" + role + '\'' + 
			",mail = '" + mail + '\'' + 
			",companyName = '" + companyName + '\'' + 
			",id = '" + id + '\'' + 
			",text = '" + text + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}
