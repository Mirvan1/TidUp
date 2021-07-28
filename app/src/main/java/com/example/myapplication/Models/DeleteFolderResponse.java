package com.example.myapplication.Models;

public class DeleteFolderResponse{
	private Boolean tf;
	private String productFolder;
	private String text;
	private String username;

	public void setTf(Boolean tf){
		this.tf = tf;
	}

	public boolean isTf(){
		return tf;
	}

	public void setProductFolder(String productFolder){
		this.productFolder = productFolder;
	}

	public String getProductFolder(){
		return productFolder;
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
			"DeleteFolderResponse{" + 
			"tf = '" + tf + '\'' + 
			",productFolder = '" + productFolder + '\'' + 
			",text = '" + text + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}
