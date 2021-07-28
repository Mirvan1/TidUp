package com.example.myapplication.Models;

public class UserShiftResponse{
    private Boolean tf;
    private String role;
    private String text;
    private String endShift;
    private String userID;
    private String startShift;
    private String username;

    public void setTf(Boolean tf){
        this.tf = tf;
    }

    public boolean isTf(){
        return tf;
    }

    public void setRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }

    public void setText(String text){
        this.text = text;
    }

    public String getText(){
        return text;
    }

    public void setEndShift(String endShift){
        this.endShift = endShift;
    }

    public String getEndShift(){
        return endShift;
    }

    public void setUserID(String userID){
        this.userID = userID;
    }

    public String getUserID(){
        return userID;
    }

    public void setStartShift(String startShift){
        this.startShift = startShift;
    }

    public String getStartShift(){
        return startShift;
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
            "UserShiftResponse{" + 
            "tf = '" + tf + '\'' + 
            ",role = '" + role + '\'' + 
            ",text = '" + text + '\'' + 
            ",endShift = '" + endShift + '\'' + 
            ",userID = '" + userID + '\'' + 
            ",startShift = '" + startShift + '\'' + 
            ",username = '" + username + '\'' + 
            "}";
        }
}
