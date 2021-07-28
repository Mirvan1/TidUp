package com.example.myapplication.Models;

public class ProductResponse{
	private String productQuantity;
	private Boolean tf;
	private String productBarcode;
	private String productNote;
	private String productFolder;
	private String productDate;
	private String productName;
	private String productPrice;
	private Object username;

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

	public void setProductFolder(String productFolder){
		this.productFolder = productFolder;
	}

	public String getProductFolder(){
		return productFolder;
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

	public void setProductPrice(String productPrice){
		this.productPrice = productPrice;
	}

	public String getProductPrice(){
		return productPrice;
	}

	public void setUsername(Object username){
		this.username = username;
	}

	public Object getUsername(){
		return username;
	}

	@Override
 	public String toString(){
		return 
			"ProductResponse{" + 
			"productQuantity = '" + productQuantity + '\'' + 
			",tf = '" + tf + '\'' + 
			",productBarcode = '" + productBarcode + '\'' + 
			",productNote = '" + productNote + '\'' + 
			",productFolder = '" + productFolder + '\'' + 
			",productDate = '" + productDate + '\'' + 
			",productName = '" + productName + '\'' + 
			",productPrice = '" + productPrice + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}
