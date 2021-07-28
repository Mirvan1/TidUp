package com.example.myapplication.View;

import com.example.myapplication.Models.DeleteFolderResponse;
import com.example.myapplication.Controller.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class deleteFolderClass {
    public String deleteMessage="";

    public void deleteFolderM(String userID,String productFolder){
        Call<DeleteFolderResponse> req=ManagerAll.getInstance().deleteFolderResponseCall(userID,productFolder);
        req.enqueue(new Callback<DeleteFolderResponse>() {
            @Override
            public void onResponse(Call<DeleteFolderResponse> call, Response<DeleteFolderResponse> response) {
                if(response.body().isTf()){
                    deleteMessage=response.body().getText();

                }
                else{
                    deleteMessage=response.body().getText();
                }
            }

            @Override
            public void onFailure(Call<DeleteFolderResponse> call, Throwable t) {
                deleteMessage="fail to connect";
            }
        });
    }
}
