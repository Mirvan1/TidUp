package com.example.myapplication.Models;

public class AddFolderResponse {
    private Boolean tf;
    private String productFolder;

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

    @Override
     public String toString(){
        return 
            "AddFolder{" + 
            "tf = '" + tf + '\'' + 
            ",productFolder = '" + productFolder + '\'' + 
            "}";
        }
}
