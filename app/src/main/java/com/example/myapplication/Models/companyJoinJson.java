package com.example.myapplication.Models;

import com.google.gson.Gson;

public class companyJoinJson {


    /**
     * IDJoin : null
     * NameJoin : null
     */

    private Object IDJoin;
    public Object NameJoin;

    public static companyJoinJson objectFromData(String str) {

        return new Gson().fromJson(str, companyJoinJson.class);
    }

    public Object getIDJoin() {
        return IDJoin;
    }

    public void setIDJoin(Object IDJoin) {
        this.IDJoin = IDJoin;
    }

    public Object getNameJoin() {
        return NameJoin;
    }

    public void setNameJoin(Object NameJoin) {
        this.NameJoin = NameJoin;
    }
}
