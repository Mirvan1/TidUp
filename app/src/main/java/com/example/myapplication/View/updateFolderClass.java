package com.example.myapplication.View;

import com.example.myapplication.Models.UpdateFolderResponse;
import com.example.myapplication.Controller.ManagerAll;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class updateFolderClass {
    String message="";

    public void updateFolderRequest(String companyID,String userID,String productFolder,String folderName){
        Call<List<UpdateFolderResponse>> req= ManagerAll.getInstance().updateFolderResponseCall(companyID,userID,productFolder,folderName);
        req.enqueue(new Callback<List<UpdateFolderResponse>>() {
            @Override
            public void onResponse(Call<List<UpdateFolderResponse>> call, Response<List<UpdateFolderResponse>> response) {
                message=response.body().get(0).getText();
            }

            @Override
            public void onFailure(Call<List<UpdateFolderResponse>> call, Throwable t) {
                message="Check your internet connection";
            }
        });




    }

}
