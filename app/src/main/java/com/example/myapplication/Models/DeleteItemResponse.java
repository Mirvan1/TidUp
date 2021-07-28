package com.example.myapplication.Models;

public class DeleteItemResponse{
	private String companyID;
	private Boolean tf;
	private String productID;
	private String companyName;
	private String text;
	private String userID;
	private String productName;
	private String username;

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

	public void setProductID(String productID){
		this.productID = productID;
	}

	public String getProductID(){
		return productID;
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

	public void setUserID(String userID){
		this.userID = userID;
	}

	public String getUserID(){
		return userID;
	}

	public void setProductName(String productName){
		this.productName = productName;
	}

	public String getProductName(){
		return productName;
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
			"DeleteItemResponse{" + 
			"companyID = '" + companyID + '\'' + 
			",tf = '" + tf + '\'' + 
			",productID = '" + productID + '\'' + 
			",companyName = '" + companyName + '\'' + 
			",text = '" + text + '\'' + 
			",userID = '" + userID + '\'' + 
			",productName = '" + productName + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}
