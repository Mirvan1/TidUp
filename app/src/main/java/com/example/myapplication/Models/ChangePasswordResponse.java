package com.example.myapplication.Models;

public class ChangePasswordResponse{
	private Boolean tf;
	private String text;
	private String username;

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

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	@Override
 	public String toString(){
		return 
			"ChangePasswordResponse{" + 
			"tf = '" + tf + '\'' + 
			",text = '" + text + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}
