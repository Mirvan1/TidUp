package com.example.myapplication.View.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class GetSharedPreferences {
 private SharedPreferences sharedPreferences;
 private  Activity activity;
 private String DEFAULT="NaN"; //userID = sharedPreferences.getString("userID",DEFAULT);
    // companyID =  sharedPreferences.getString("companyID",DEFAULT);
 //public static String userID;
    //public static String companyID;
    // sharedPreferences=activity.getApplicationContext().getSharedPreferences("session",0);

 public  GetSharedPreferences(Activity activity){
    this.activity=activity;
 }

 public  SharedPreferences getSession(){
     sharedPreferences = activity.getApplicationContext().getSharedPreferences("session", Context.MODE_PRIVATE);
    return  sharedPreferences; }

    public void setSession(String userID,String companyID){
        sharedPreferences=activity.getApplicationContext().getSharedPreferences("session",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("userID",userID);
        editor.putString("companyID",companyID);
        editor.apply();
 }

public void outSession(){
    sharedPreferences=activity.getApplicationContext().getSharedPreferences("session",0);
    SharedPreferences.Editor editor=sharedPreferences.edit();
    editor.clear();
    editor.apply();
}
}
