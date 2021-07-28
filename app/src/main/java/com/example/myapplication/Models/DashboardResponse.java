package com.example.myapplication.Models;

public class DashboardResponse{
	private Integer item;
	private String quantity;
	private Integer folder;
	private Integer manager;
	private String price;
	private Integer employee;

	public void setItem(Integer item){
		this.item = item;
	}

	public Integer getItem(){
		return item;
	}

	public void setQuantity(String quantity){
		this.quantity = quantity;
	}

	public String getQuantity(){
		return quantity;
	}

	public void setFolder(Integer folder){
		this.folder = folder;
	}

	public Integer getFolder(){
		return folder;
	}

	public void setManager(Integer manager){
		this.manager = manager;
	}

	public Integer getManager(){
		return manager;
	}

	public void setPrice(String price){
		this.price = price;
	}

	public String getPrice(){
		return price;
	}

	public void setEmployee(Integer employee){
		this.employee = employee;
	}

	public Integer getEmployee(){
		return employee;
	}

	@Override
 	public String toString(){
		return 
			"{" +
			"item = '" + item + '\'' + 
			",quantity = '" + quantity + '\'' + 
			",folder = '" + folder + '\'' + 
			",manager = '" + manager + '\'' + 
			",price = '" + price + '\'' + 
			",employee = '" + employee + '\'' + 
			"}";
		}
}
