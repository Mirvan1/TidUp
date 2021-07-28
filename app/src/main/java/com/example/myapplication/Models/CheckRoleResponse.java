package com.example.myapplication.Models;

public class CheckRoleResponse{
	private String roleVar;
	private Boolean tf;
	private String text;

	public void setRoleVar(String roleVar){
		this.roleVar = roleVar;
	}

	public String getRoleVar(){
		return roleVar;
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

	@Override
 	public String toString(){
		return 
			"CheckRoleResponse{" + 
			"roleVar = '" + roleVar + '\'' + 
			",tf = '" + tf + '\'' + 
			",text = '" + text + '\'' + 
			"}";
		}
}
