package com.example.myapplication.Models;

import com.google.gson.Gson;

public class companyCreateJson {
    /**
     * name : null
     * ID : M3mzlrYRVH
     */

    private Object name;
    private String ID;

    public static companyCreateJson objectFromData(String str) {

        return new Gson().fromJson(str, companyCreateJson.class);
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
