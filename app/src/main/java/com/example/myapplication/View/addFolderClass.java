package com.example.myapplication.View;

import com.example.myapplication.Models.AddFolderResponse;
import com.example.myapplication.Controller.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class addFolderClass {
//
//    public void defineVar(){
//        String userID=sendFolderValue.userIDfromLogin;
//        String companyID=sendFolderValue.companyIDfromLogin;
//        String projectFolder=sendFolderValue.getFolderNamefromTextView;
//        addFolderResponse(userID,companyID,projectFolder);
//
//    }

    public void addFolderResponse(String userID, String companyID, String projectFolder){
        Call<AddFolderResponse> req=ManagerAll.getInstance().addFolderResponseCall(userID,companyID,projectFolder);
        req.enqueue(new Callback<AddFolderResponse>() {
            @Override
            public void onResponse(Call<AddFolderResponse> call, Response<AddFolderResponse> response) {
//                if(response.body().isTf()){
//                    fragmentMainPage a=new fragmentMainPage();
//                    ((FragmentActivity) context).getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.listViewLayout,a,"fragment")
//                            .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//                            .commit();
//                }
            }

            @Override
            public void onFailure(Call<AddFolderResponse> call, Throwable t) {

            }
        });


    }
}
