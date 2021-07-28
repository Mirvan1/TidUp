package com.example.myapplication.Models;

public class ListUserResponse{
	private Boolean tf;
	private String role;
	private String text;
	private String userID;
	private String email;
	private String username;

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
			"ListUserResponse{" + 
			"tf = '" + tf + '\'' + 
			",role = '" + role + '\'' + 
			",text = '" + text + '\'' + 
			",userID = '" + userID + '\'' + 
			",email = '" + email + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}
