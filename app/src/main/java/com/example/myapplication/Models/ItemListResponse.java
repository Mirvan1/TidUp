package com.example.myapplication.Models;

public class ItemListResponse{
	private String productIDList;
	private String productQuantityList;
	private String tx;
	private String usernameList;
	private String productNoteList;
	private String productNameList;
	private String productPhotoList;
	private String productDateList;
	private String productFolderList;
	private Boolean tf;
	private String productBarcodeList;
	private String productPriceList;
	private String userIDList;
	private String companyIDList;

	public void setProductIDList(String productIDList){
		this.productIDList = productIDList;
	}

	public String getProductIDList(){
		return productIDList;
	}

	public void setProductQuantityList(String productQuantityList){
		this.productQuantityList = productQuantityList;
	}

	public String getProductQuantityList(){
		return productQuantityList;
	}

	public void setTx(String tx){
		this.tx = tx;
	}

	public String getTx(){
		return tx;
	}

	public void setUsernameList(String usernameList){
		this.usernameList = usernameList;
	}

	public String getUsernameList(){
		return usernameList;
	}

	public void setProductNoteList(String productNoteList){
		this.productNoteList = productNoteList;
	}

	public String getProductNoteList(){
		return productNoteList;
	}

	public void setProductNameList(String productNameList){
		this.productNameList = productNameList;
	}

	public String getProductNameList(){
		return productNameList;
	}

	public void setProductPhotoList(String productPhotoList){
		this.productPhotoList = productPhotoList;
	}

	public String getProductPhotoList(){
		return productPhotoList;
	}

	public void setProductDateList(String productDateList){
		this.productDateList = productDateList;
	}

	public String getProductDateList(){
		return productDateList;
	}

	public void setProductFolderList(String productFolderList){
		this.productFolderList = productFolderList;
	}

	public String getProductFolderList(){
		return productFolderList;
	}

	public void setTf(Boolean tf){
		this.tf = tf;
	}

	public boolean isTf(){
		return tf;
	}

	public void setProductBarcodeList(String productBarcodeList){
		this.productBarcodeList = productBarcodeList;
	}

	public String getProductBarcodeList(){
		return productBarcodeList;
	}

	public void setProductPriceList(String productPriceList){
		this.productPriceList = productPriceList;
	}

	public String getProductPriceList(){
		return productPriceList;
	}

	public void setUserIDList(String userIDList){
		this.userIDList = userIDList;
	}

	public String getUserIDList(){
		return userIDList;
	}

	public void setCompanyIDList(String companyIDList){
		this.companyIDList = companyIDList;
	}

	public String getCompanyIDList(){
		return companyIDList;
	}

	@Override
 	public String toString(){
		return 
			"ItemListResponse{" + 
			"productIDList = '" + productIDList + '\'' + 
			",productQuantityList = '" + productQuantityList + '\'' + 
			",tx = '" + tx + '\'' + 
			",usernameList = '" + usernameList + '\'' + 
			",productNoteList = '" + productNoteList + '\'' + 
			",productNameList = '" + productNameList + '\'' + 
			",productPhotoList = '" + productPhotoList + '\'' + 
			",productDateList = '" + productDateList + '\'' + 
			",productFolderList = '" + productFolderList + '\'' + 
			",tf = '" + tf + '\'' + 
			",productBarcodeList = '" + productBarcodeList + '\'' + 
			",productPriceList = '" + productPriceList + '\'' + 
			",userIDList = '" + userIDList + '\'' + 
			",companyIDList = '" + companyIDList + '\'' + 
			"}";
		}
}
