package com.example.myapplication.Models;

public class FindBarcodeResponse{
	private String productPhoto;
	private String productFolder;
	private String companyName;
	private String productDate;
	private String productName;
	private String companyID;
	private String productQuantity;
	private Boolean tf;
	private String productBarcode;
	private String productNote;
	private String text;
	private String productPrice;
	private String username;

	public void setProductPhoto(String productPhoto){
		this.productPhoto = productPhoto;
	}

	public String getProductPhoto(){
		return productPhoto;
	}

	public void setProductFolder(String productFolder){
		this.productFolder = productFolder;
	}

	public String getProductFolder(){
		return productFolder;
	}

	public void setCompanyName(String companyName){
		this.companyName = companyName;
	}

	public String getCompanyName(){
		return companyName;
	}

	public void setProductDate(String productDate){
		this.productDate = productDate;
	}

	public String getProductDate(){
		return productDate;
	}

	public void setProductName(String productName){
		this.productName = productName;
	}

	public String getProductName(){
		return productName;
	}

	public void setCompanyID(String companyID){
		this.companyID = companyID;
	}

	public String getCompanyID(){
		return companyID;
	}

	public void setProductQuantity(String productQuantity){
		this.productQuantity = productQuantity;
	}

	public String getProductQuantity(){
		return productQuantity;
	}

	public void setTf(Boolean tf){
		this.tf = tf;
	}

	public boolean isTf(){
		return tf;
	}

	public void setProductBarcode(String productBarcode){
		this.productBarcode = productBarcode;
	}

	public String getProductBarcode(){
		return productBarcode;
	}

	public void setProductNote(String productNote){
		this.productNote = productNote;
	}

	public String getProductNote(){
		return productNote;
	}

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	public void setProductPrice(String productPrice){
		this.productPrice = productPrice;
	}

	public String getProductPrice(){
		return productPrice;
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
			"FindBarcodeResponse{" + 
			"productPhoto = '" + productPhoto + '\'' + 
			",productFolder = '" + productFolder + '\'' + 
			",companyName = '" + companyName + '\'' + 
			",productDate = '" + productDate + '\'' + 
			",productName = '" + productName + '\'' + 
			",companyID = '" + companyID + '\'' + 
			",productQuantity = '" + productQuantity + '\'' + 
			",tf = '" + tf + '\'' + 
			",productBarcode = '" + productBarcode + '\'' + 
			",productNote = '" + productNote + '\'' + 
			",text = '" + text + '\'' + 
			",productPrice = '" + productPrice + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}
